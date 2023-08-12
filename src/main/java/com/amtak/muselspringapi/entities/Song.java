package com.amtak.muselspringapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "song_loop_id", referencedColumnName = "id")
    private SongLoop songLoop;

    @OneToOne
    @JoinColumn(name = "song_file_id", referencedColumnName = "id")
    private SongFile songFile;

    public Song() {
    }

    public Song(
            String title,
            Double loopStart,
            Double loopEnd,
            Double fileDuration
    ) {
        this.songLoop = new SongLoop();
        this.songFile = new SongFile();

        patchTitle(title);
        patchFileDuration(fileDuration);
        patchLoopStartEnd(loopStart, loopEnd);
    }

    public Integer getId() {
        return id;
    }

    public Song setId(Integer id) {
        this.id = id;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song patchTitle(String title) {
        if(title == null || title.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        this.title = title;

        return this;
    }

    public SongLoop getSongLoop() {
        return songLoop;
    }

    public Song setSongLoop(SongLoop songLoop) {
        this.songLoop = songLoop;

        return this;
    }

    public SongFile getSongFile() {
        return songFile;
    }

    public Song setSongFile(SongFile songFile) {
        this.songFile = songFile;

        return this;
    }

    public Double fetchLoopStart() throws IllegalStateException {
        if(songLoop == null) {
            throw new IllegalStateException();
        }

        return songLoop.getLoopStart();
    }

    public Double fetchLoopEnd() throws IllegalStateException {
        if(songLoop == null) {
            throw new IllegalStateException();
        }

        return songLoop.getLoopEnd();
    }

    public Song patchLoopStartEnd(Double loopStart, Double loopEnd) {
        Double fileDuration = fetchFileDuration();

        if(loopStart == null || loopEnd == null) {
            throw new IllegalArgumentException();
        }

        if(loopStart < 0 || loopEnd < 0) {
            throw new IllegalArgumentException();
        }

        if(loopStart > fileDuration || loopEnd > fileDuration) {
            throw new IllegalArgumentException();
        }

        if(loopEnd - loopStart < 0) {
            throw new IllegalArgumentException();
        }

        songLoop.setLoopStart(loopStart);
        songLoop.setLoopEnd(loopEnd);

        return this;
    }

    public Double fetchFileDuration() throws IllegalStateException {
        if(songFile == null) {
            throw new IllegalStateException();
        }

        return songFile.getFileDuration();
    }

    public Song patchFileDuration(Double fileDuration) {
        if(fileDuration == null || fileDuration < 0) {
            throw new IllegalArgumentException();
        }

        songFile.setFileDuration(fileDuration);

        Double loopStart = fetchLoopStart();
        Double loopEnd = fetchLoopEnd();

        if(loopStart != null && loopEnd != null) {
            double endDifference = fileDuration - loopEnd;

            if(endDifference < 0) {
                loopEnd = fileDuration;
                loopStart = loopStart + endDifference;

                if(loopStart < 0) {
                    loopStart = 0.0d;
                }

                songLoop.setLoopStart(loopStart);
                songLoop.setLoopEnd(loopEnd);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", songLoop=" + songLoop +
                ", songFile=" + songFile +
                '}';
    }
}
