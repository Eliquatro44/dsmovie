package com.devsuperior3.dsmovie3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior3.dsmovie3.entities.Score;
import com.devsuperior3.dsmovie3.entities.ScorePK;



public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

	
}
