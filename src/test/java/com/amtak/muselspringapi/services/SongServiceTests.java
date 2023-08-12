package com.amtak.muselspringapi.services;

import com.amtak.muselspringapi.dto.SongDto;
import com.amtak.muselspringapi.repositories.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class SongServiceTests {
    private static final int GOOD_ID = 1;
    private static final int BAD_ID = -99;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    private long songsCount;

    @BeforeEach
    public void setUp() {
        songsCount = songRepository.count();
    }

    @AfterEach
    public void tearDown() {

    }

    //// Common ////

    public static void assertSong(SongDto songDto) {
        assertNotNull(songDto);

        assertNotNull(songDto.getTitle());
        assert(songDto.getTitle().trim().length() > 0);

        assertNotNull(songDto.getFileDuration());
        assert(songDto.getFileDuration() >= 0);

        assertNotNull(songDto.getLoopStart());
        assert(songDto.getLoopStart() >= 0);
        assert(songDto.getLoopStart() <= songDto.getFileDuration());

        assertNotNull(songDto.getLoopEnd());
        assert(songDto.getLoopEnd() >= 0);
        assert(songDto.getLoopEnd() <= songDto.getFileDuration());

        assert(songDto.getLoopStart() <= songDto.getLoopEnd());
    }

    //// Song to Dto ////

    @Test
    public void testSongToDto() {
        // TODO
    }

    //// Get all Songs ////

    @Test
    public void testGetAllSongs() {
        List<SongDto> songDtos = songService.getAllSongs();

        for(SongDto songDto : songDtos) {
            System.out.println(songDto);

            assertSong(songDto);
        }
    }

    //// Get Song by id ////

    @Test
    public void testGetSongById() {
        SongDto songDto = songService.getSongById(GOOD_ID);

        System.out.println(songDto);

        assertSong(songDto);
        assertEquals(GOOD_ID, songDto.getId());
    }

    @Test
    public void testGetSongByIdNotFound() {
        assertThrows(EntityNotFoundException.class, () -> songService.getSongById(BAD_ID));
    }

    //// Delete Song by id ////

    @Test
    public void testDeleteSongById() {
        SongDto beforeDeleteSongDto = songService.getSongById(GOOD_ID);
        SongDto afterDeleteSongDto = songService.deleteSongById(GOOD_ID);

        assertSong(afterDeleteSongDto);
        assertEquals(GOOD_ID, afterDeleteSongDto.getId());
        assertEquals(beforeDeleteSongDto, afterDeleteSongDto);
        assertEquals(songsCount - 1, songRepository.count());
    }

    @Test
    public void testDeleteSongByIdNotFound() {
        assertThrows(EntityNotFoundException.class, () -> songService.deleteSongById(BAD_ID));
    }
}
