package com.amtak.muselspringapi.dto;

public class SongDto {
    private Integer id;

    private String title;

    private Double loopStart;

    private Double loopEnd;

    private Double fileDuration;

    private byte[] fileData;

    public SongDto() {
    }

    public SongDto(Integer id, String title, Double loopStart, Double loopEnd, Double fileDuration, byte[] fileData) {
        this.id = id;
        this.title = title;
        this.loopStart = loopStart;
        this.loopEnd = loopEnd;
        this.fileDuration = fileDuration;
        this.fileData = fileData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Double getFileDuration() {
        return fileDuration;
    }

    public void setFileDuration(Double fileDuration) {
        this.fileDuration = fileDuration;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
