package com.amtak.muselspringapi.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SongTests {
    public final static String TITLE = "Battle Against the Ones We Inevitably Confront";
    public final static Double LOOP_START = 30.337349397590373;
    public final static Double LOOP_END = 146.0;
    public final static Double FILE_DURATION = 146.25066666666666;

    private Song song;

    @BeforeEach
    public void setUp() {
        song = new Song(
                TITLE,
                LOOP_START,
                LOOP_END,
                FILE_DURATION
        );
    }

    @AfterEach
    public void tearDown() {
        song = null;
    }

    //// Common ////

    private void assertSongValues(String title, Double loopStart, Double loopEnd, Double fileDuration, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(loopStart, song.fetchLoopStart());
        assertEquals(loopEnd, song.fetchLoopEnd());
        assertEquals(fileDuration, song.fetchFileDuration());
    }

    private void assertSongValues(String title, Double loopStart, Double loopEnd, Double fileDuration) {
        assertSongValues(title, loopStart, loopEnd, fileDuration, song);
    }

    private void assertSong() {
        assertSongValues(TITLE, LOOP_START, LOOP_END, FILE_DURATION);
    }

    //// Constructors ////

    @Test
    public void testNewSong() {
        // TODO
    }

    //// Song id ////

    @Test
    public void testGetId() {
        assertNull(song.getId());
    }

    @Test
    public void testSetId() {
        Integer id = 99;

        Song returnedSong = song.setId(id);

        assertSame(song, returnedSong);
        assertEquals(id, song.getId());
        assertSong();
    }

    //// Song title ////

    @Test
    public void testGetTitle() {
        assertEquals(TITLE, song.getTitle());
        assertSong();
    }

    @Test
    public void testPatchTitle() {
        String title = "That of the Name";

        Song returnedSong = song.patchTitle(title);

        assertSame(song, returnedSong);
        assertSongValues(title, LOOP_START, LOOP_END, FILE_DURATION);
    }

    @Test
    public void testPatchTitleIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> song.patchTitle(null));
        assertThrows(IllegalArgumentException.class, () -> song.patchTitle(""));
        assertThrows(IllegalArgumentException.class, () -> song.patchTitle("   "));
        assertThrows(IllegalArgumentException.class, () -> song.patchTitle("\t"));
        assertThrows(IllegalArgumentException.class, () -> song.patchTitle("\n"));
    }

    //// Song songLoop ////

    @Test
    public void testGetSongLoop() {
        // TODO
    }

    @Test
    public void testSetSongLoop() {
        // TODO
    }

    //// Song songFile ////

    @Test
    public void testGetSongFile() {
        // TODO
    }

    @Test
    public void testSetSongFile() {
        // TODO
    }

    //// Song loop params ////

    @Test
    public void testFetchLoopStart() {
        // TODO
    }

    @Test
    public void testFetchLoopEnd() {
        // TODO
    }

    @Test
    public void testPatchLoopStartEnd() {
        // TODO
    }

    //// Song file duration ////

    @Test
    public void testFetchFileDuration() {
        // TODO
    }

    @Test
    public void testPatchFileDuration() {
        // TODO
    }
}
