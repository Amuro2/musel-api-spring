package com.amtak.muselspringapi.repositories;

import com.amtak.muselspringapi.entities.SongLoop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongLoopRepository extends JpaRepository<SongLoop, Integer> {
}
