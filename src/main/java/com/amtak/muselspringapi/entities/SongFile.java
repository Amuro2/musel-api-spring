package com.amtak.muselspringapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "song_file")
public class SongFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_duration")
    private Double fileDuration;

    public SongFile() {
    }

    public SongFile(Double fileDuration) {
        this.fileDuration = fileDuration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFileDuration() {
        return fileDuration;
    }

    public void setFileDuration(Double fileDuration) {
        this.fileDuration = fileDuration;
    }

    @Override
    public String toString() {
        return "SongFile{" +
                "id=" + id +
                ", fileDuration=" + fileDuration +
                '}';
    }
}
