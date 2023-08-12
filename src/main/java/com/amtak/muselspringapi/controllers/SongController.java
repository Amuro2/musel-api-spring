package com.amtak.muselspringapi.controllers;

import com.amtak.muselspringapi.dto.SongDto;
import com.amtak.muselspringapi.services.SongFileService;
import com.amtak.muselspringapi.services.SongSaveService;
import com.amtak.muselspringapi.services.SongService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    private SongService songService;

    @Autowired
    private SongSaveService songSaveService;

    @Autowired
    private SongFileService songFileService;

    @GetMapping("/all")
    public List<SongDto> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDto> getSongById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(songService.getSongById(id));
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> serveFile(@PathVariable int id) {
        try {
            Resource resource = songFileService.loadAsResource(id);
            byte[] byteArray = songFileService.loadAsByteArray(id);

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(byteArray);
        } catch(IOException | RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SongDto> postSong(
            @RequestParam("title") String title,
            @RequestParam("loopStart") Double loopStart,
            @RequestParam("loopEnd") Double loopEnd,
            @RequestParam("fileDuration") Double fileDuration,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        try {
            return ResponseEntity.ok(songSaveService.postSong(
                    title,
                    loopStart,
                    loopEnd,
                    fileDuration,
                    multipartFile
            ));
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch(EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping
    public ResponseEntity<SongDto> putSong(
            @RequestParam("id") Integer id,
            @RequestParam("title") String title,
            @RequestParam("loopStart") Double loopStart,
            @RequestParam("loopEnd") Double loopEnd,
            @RequestParam("fileDuration") Double fileDuration,
            @RequestParam(value = "file", required = false) MultipartFile multipartFile
    ) {
        try {
            return ResponseEntity.ok(songSaveService.putSong(
                    id,
                    title,
                    loopStart,
                    loopEnd,
                    fileDuration,
                    multipartFile
            ));
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SongDto> deleteSongById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(songService.deleteSongById(id));
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
