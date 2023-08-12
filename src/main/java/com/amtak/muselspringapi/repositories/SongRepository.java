package com.amtak.muselspringapi.repositories;

import com.amtak.muselspringapi.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
