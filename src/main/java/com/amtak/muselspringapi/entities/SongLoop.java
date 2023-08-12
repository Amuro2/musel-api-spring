package com.amtak.muselspringapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "song_loop")
public class SongLoop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "loop_start")
    private Double loopStart;

    @Column(name = "loop_end")
    private Double loopEnd;

    public SongLoop() {
    }

    public SongLoop(Double loopStart, Double loopEnd) {
        this.loopStart = loopStart;
        this.loopEnd = loopEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLoopStart() {
        return loopStart;
    }

    public void setLoopStart(Double loopStart) {
        this.loopStart = loopStart;
    }

    public Double getLoopEnd() {
        return loopEnd;
    }

    public void setLoopEnd(Double loopEnd) {
        this.loopEnd = loopEnd;
    }

    @Override
    public String toString() {
        return "SongLoop{" +
                "id=" + id +
                ", loopStart=" + loopStart +
                ", loopEnd=" + loopEnd +
                '}';
    }
}
