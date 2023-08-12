package com.amtak.muselspringapi.services;

import com.amtak.muselspringapi.dto.SongDto;
import com.amtak.muselspringapi.entities.Song;
import com.amtak.muselspringapi.repositories.SongFileRepository;
import com.amtak.muselspringapi.repositories.SongLoopRepository;
import com.amtak.muselspringapi.repositories.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SongSaveService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongLoopRepository songLoopRepository;

    @Autowired
    private SongFileRepository songFileRepository;

    @Autowired
    private SongService songService;

    @Autowired
    private SongFileService songFileService;

    public SongDto postSong(
            String title,
            Double loopStart,
            Double loopEnd,
            Double fileDuration,
            MultipartFile multipartFile
    ) {
        // TODO EntityExistsException

        Song song = new Song(
                title,
                loopStart,
                loopEnd,
                fileDuration
        );

        songFileRepository.save(song.getSongFile());
        songLoopRepository.save(song.getSongLoop());

        Song afterPostSong = songRepository.save(song);

        songFileService.store(multipartFile, afterPostSong.getId());

        return songService.songToDto(afterPostSong);
    }

    public SongDto putSong(
            Integer id,
            String title,
            Double loopStart,
            Double loopEnd,
            Double fileDuration,
            MultipartFile multipartFile
    ) {
        Song song = songRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if(title != null) {
            song.patchTitle(title);
        }

        if(loopStart != null && loopEnd != null) {
            song.patchLoopStartEnd(loopStart, loopEnd);
        }

        if(fileDuration != null) {
            song.patchFileDuration(fileDuration);
        }

        if(multipartFile != null) {
            songFileService.store(multipartFile, id);
        }

        return songService.songToDto(songRepository.save(song));
    }
}
