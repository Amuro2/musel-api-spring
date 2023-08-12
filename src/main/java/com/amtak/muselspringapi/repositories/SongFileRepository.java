package com.amtak.muselspringapi.repositories;

import com.amtak.muselspringapi.entities.SongFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongFileRepository extends JpaRepository<SongFile, Integer> {
}
