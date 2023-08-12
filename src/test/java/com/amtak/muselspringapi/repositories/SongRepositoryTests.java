package com.amtak.muselspringapi.repositories;

import com.amtak.muselspringapi.entities.Song;
import com.amtak.muselspringapi.entities.SongTests;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SongRepositoryTests {
    private static final int GOOD_ID = 1;
    private static final int BAD_ID = -99;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongLoopRepository songLoopRepository;

    @Autowired
    private SongFileRepository songFileRepository;

    private Song song;

    private long songsCount;

    @BeforeEach
    public void setUp() {
        song = new Song(SongTests.TITLE, 0d, SongTests.FILE_DURATION, SongTests.FILE_DURATION);
        songsCount = songRepository.count();
    }

    @AfterEach
    public void tearDown() {
        song = null;
    }

    @Test
    public void testFindAll() {
        List<Song> songs = songRepository.findAll();

        for(Song song : songs) {
            System.out.println(song);
        }
    }

    @Test
    public void testSave() {
        songFileRepository.save(song.getSongFile());
        songLoopRepository.save(song.getSongLoop());
        Song afterSaveSong = songRepository.save(song);

        System.out.println(afterSaveSong);

        assertEquals(songsCount + 1, songRepository.count());
        assertSame(afterSaveSong, song);
    }

    @Test
    public void testFindById() {
        song = songRepository.findById(GOOD_ID).orElseThrow();

        System.out.println(song);
        System.out.println(song.getSongLoop());
        System.out.println(song.getSongFile());

        assertEquals(GOOD_ID, song.getId());
    }

    @Test
    public void testFindByIdNoSuchElement() {
        assertThrows(NoSuchElementException.class, () -> songRepository.findById(BAD_ID).orElseThrow());
    }
}
