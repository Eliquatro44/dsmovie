package com.devsuperior3.dsmovie3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior3.dsmovie3.ResourceNotFoundException;
import com.devsuperior3.dsmovie3.dto.MovieDTO;
import com.devsuperior3.dsmovie3.dto.ScoreDTO;
import com.devsuperior3.dsmovie3.entities.Movie;
import com.devsuperior3.dsmovie3.entities.Score;
import com.devsuperior3.dsmovie3.entities.User;
import com.devsuperior3.dsmovie3.repositories.MovieRepository;
import com.devsuperior3.dsmovie3.repositories.ScoreRepository;
import com.devsuperior3.dsmovie3.repositories.UserRepository;

@Service
public class MovieService {
	

	@Autowired
	private MovieRepository repository;

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
		Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
		return page;
	}
	
	/*
	public Page<MovieDTO> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(movie -> new MovieDTO(movie));
	}*/
	
	/*
	@Transactional(readOnly = true)
	public Page<movieDTO> findAll(Pageable pageable){
		Page<Movie> result = repository.findAll(pageable);
		Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
		return page;
	} */
	

    public MovieDTO findById(Long id) {
        // Busca o filme no repositório usando o ID
        Movie movie = repository.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("Movie not found with ID: " + id));
        // Converte a entidade Movie para DTO e retorna
        return new MovieDTO(movie);
    }
    
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto) {
        // Busca ou insere o usuário no banco de dados
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.save(user);
        }

        // Busca o filme pelo ID
        Movie movie = movieRepository.findById(dto.getMovieId()).orElseThrow(() -> 
            new ResourceNotFoundException("Movie not found with ID: " + dto.getMovieId()));

        // Salva o score no banco
        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());
        score = scoreRepository.save(score);

        // Atualiza o score médio do filme
        double sum = 0.0;
        for (Score s : movie.getScores()) {
            sum += s.getValue();
        }
        double avg = sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());
        movie = movieRepository.save(movie);

        return new MovieDTO(movie);
    }
}