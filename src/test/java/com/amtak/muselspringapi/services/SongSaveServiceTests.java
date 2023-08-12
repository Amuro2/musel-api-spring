package com.amtak.muselspringapi.services;

import com.amtak.muselspringapi.repositories.SongRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class SongSaveServiceTests {
    @Autowired
    private SongSaveService songSaveService;

    @Autowired
    private SongRepository songRepository;

    private long songsCount;

    @BeforeEach
    public void setUp() {
        songsCount = songRepository.count();
    }

    @AfterEach
    public void tearDown() {

    }

    //// Post Song ////

    @Test
    public void testPostSong() {
        // TODO songSaveService.postSong (successful)
        // Assert a song has been created
    }

    @Test
    public void testPostSongIllegalArgument() {
        // TODO songSaveService.postSong (IllegalArgument)
        // Assert an IllegalArgumentException is thrown
    }

    @Test
    public void testPostSongEntityExists() {
        // TODO songSaveService.postSong (EntityExists)
        // Assert an EntityExistsException is thrown
    }

    //// Put Song ////

    @Test
    public void testPutSong() {
        // TODO songSaveService.putSong (successful)
        // Assert the song has been updated
    }

    @Test
    public void testPutSongIllegalArgument() {
        // TODO songSaveService.putSong (IllegalArgument)
        // Assert an IllegalArgumentException is thrown
    }

    @Test
    public void testPutSongEntityExists() {
        // TODO songSaveService.putSong (EntityNotFound)
        // Assert an EntityNotFoundException is thrown
    }
}
