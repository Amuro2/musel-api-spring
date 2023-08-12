package com.amtak.muselspringapi.repositories;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class SongFileRepositoryTests {
    @Autowired
    private SongFileRepository songFileRepository;

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {

    }
}
