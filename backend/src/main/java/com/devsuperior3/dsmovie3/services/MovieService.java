package com.devsuperior3.dsmovie3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior3.dsmovie3.dto.MovieDTO;
import com.devsuperior3.dsmovie3.entities.Movie;
import com.devsuperior3.dsmovie3.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	

}
