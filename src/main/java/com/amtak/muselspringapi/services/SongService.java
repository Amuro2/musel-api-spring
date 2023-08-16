package com.amtak.muselspringapi.services;

import com.amtak.muselspringapi.dto.SongDto;
import com.amtak.muselspringapi.entities.Song;
import com.amtak.muselspringapi.repositories.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongFileService songFileService;

    public SongDto songToDto(Song song) {
        byte[] fileData = null;
        boolean isFileMissing = false;

        try {
//            fileData = songFileService.loadAsByteArray(song.getId());
            songFileService.loadAsResource(song.getId());
        } catch(Exception e) {
            isFileMissing = true;
        }

        return new SongDto(
                song.getId(),
                song.getTitle(),
                song.fetchLoopStart(),
                song.fetchLoopEnd(),
                song.fetchFileDuration(),
                fileData,
                isFileMissing
        );
    }

    public List<SongDto> getAllSongs() {
        List<SongDto> songDtos = new ArrayList<>();

        for(Song song : songRepository.findAll()) {
            songDtos.add(songToDto(song));
        }

        return songDtos;
    }

    public SongDto getSongById(int id) {
        return songToDto(songRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public SongDto deleteSongById(int id) {
        SongDto songDto = getSongById(id);

        songFileService.delete(id);

        songRepository.deleteById(id);

        return songDto;
    }
}
