package com.amtak.muselspringapi.controllers;

import com.amtak.muselspringapi.dto.SongDto;
import com.amtak.muselspringapi.repositories.SongRepository;
import com.amtak.muselspringapi.services.SongService;
import com.amtak.muselspringapi.services.SongServiceTests;
import com.amtak.muselspringapi.util.MvcMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc
@Validated
@Transactional
public class SongControllerTests {
    private static final int GOOD_ID = 1;
    private static final int BAD_ID = -99;

//    private static final Double NEGATIVE = -99d;
//    private static final Double TOO_BIG = 9999999d;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    private MvcMocks mvcMocks;

    private long songsCount;

    @BeforeEach
    public void setUp() {
        mvcMocks = new MvcMocks(mockMvc, objectMapper);
        songsCount = songRepository.count();
    }

    @AfterEach
    public void tearDown() {
        mvcMocks = null;
    }

    //// Common ////

    private static void assertSong(SongDto songDto) {
        SongServiceTests.assertSong(songDto);
    }

    //// Get all Songs ////

    @Test
    public void testGetAllSongs() {
        assertDoesNotThrow(() -> {
            String contentAsString = mvcMocks.get("/api/song/all");
            List<SongDto> songDtos = Arrays.asList(objectMapper.readValue(contentAsString, SongDto[].class));

            for(SongDto songDto : songDtos) {
                System.out.println(songDto);

                assertSong(songDto);
            }
        });
    }

    //// Get Song by id ////

    @Test
    public void testGetSongById() {
        assertDoesNotThrow(() -> {
            String contentAsString = mvcMocks.get("/api/song/" + GOOD_ID);
            SongDto songDto = objectMapper.readValue(contentAsString, SongDto.class);

            assertSong(songDto);
            assertEquals(GOOD_ID, songDto.getId());
        });
    }

    @Test
    public void testGetSongByIdNotFound() {
        assertDoesNotThrow(() -> {
            mvcMocks.get("/api/song/" + BAD_ID, HttpStatus.NOT_FOUND);
        });
    }

    //// Serve file ////

    @Test
    public void testServeFile() {
        // TODO
    }

    @Test
    public void testServeFileNotFound() {
        // TODO
    }

    //// Post Song ////

    @Test
    public void testPostSong() {
        // TODO
    }

    @Test
    public void testPostSongConflict() {
        // TODO
    }

    @Test
    public void testPostSongBadRequest() {
        // TODO
    }

    //// Put Song ////

    @Test
    public void testPutSong() {
        // TODO
    }

    @Test
    public void testPutSongNotFound() {
        // TODO
    }

    @Test
    public void testPutSongBadRequest() {
        // TODO
    }

    //// Delete Song by id ////

    @Test
    public void testDeleteSongById() {
        assertDoesNotThrow(() -> {
            SongDto beforeDeleteSongDto = songService.getSongById(GOOD_ID);
            String contentAsString = mvcMocks.delete("/api/song/" + GOOD_ID);
            SongDto afterDeleteSongDto = objectMapper.readValue(contentAsString, SongDto.class);

            assertSong(afterDeleteSongDto);
            assertEquals(beforeDeleteSongDto, afterDeleteSongDto);
            assertEquals(songsCount - 1, songRepository.count());
        });
    }

    @Test
    public void testDeleteSongByIdNotFound() {
        assertDoesNotThrow(() -> {
            mvcMocks.delete("/api/song/" + BAD_ID, HttpStatus.NOT_FOUND);
        });
    }
}
