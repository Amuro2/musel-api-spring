package com.amtak.muselspringapi.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SongFileServiceTests {
    @Autowired
    private SongFileService songFileService;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testStore() {
        // TODO
    }

    @Test
    public void testStoreException() {
        // TODO
    }

    @Test
    public void testLoadAsResource() {
        // TODO
    }

    @Test
    public void testLoadAsResourceException() {
        // TODO
    }

    @Test
    public void testLoadAsByteArray() {
        // TODO
    }

    @Test
    public void testLoadAsByteArrayException() {
        // TODO
    }
}
