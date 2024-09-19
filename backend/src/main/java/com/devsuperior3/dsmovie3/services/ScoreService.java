package com.devsuperior3.dsmovie3.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior3.dsmovie3.dto.MovieDTO;
import com.devsuperior3.dsmovie3.dto.ScoreDTO;
import com.devsuperior3.dsmovie3.entities.Movie;
import com.devsuperior3.dsmovie3.entities.Score;
import com.devsuperior3.dsmovie3.entities.User;
import com.devsuperior3.dsmovie3.repositories.MovieRepository;
import com.devsuperior3.dsmovie3.repositories.ScoreRepository;
import com.devsuperior3.dsmovie3.repositories.UserRepository;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto) {
        // Encontrar ou criar o usuário
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        // Encontrar o filme pelo ID
        Movie movie = movieRepository.findById(dto.getMovieId()).orElseThrow();

        // Criar e salvar o score
        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());
        score = scoreRepository.saveAndFlush(score);

        // Calcular a média de scores
        double sum = 0.0;
        for (Score s : movie.getScores()) {
            sum += s.getValue();
        }
        double avg = sum / movie.getScores().size();

        // Atualizar informações do filme
        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        // Salvar o filme atualizado
        movie = movieRepository.save(movie);

        // Retornar MovieDTO
        return new MovieDTO(movie);
    }
}