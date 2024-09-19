package com.devsuperior3.dsmovie3.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior3.dsmovie3.entities.Movie;
import com.devsuperior3.dsmovie3.entities.Score;
import com.devsuperior3.dsmovie3.entities.User;
import com.devsuperior3.dsmovie3.repositories.MovieRepository;
import com.devsuperior3.dsmovie3.repositories.ScoreRepository;
import com.devsuperior3.dsmovie3.repositories.UserRepository;




public class ScoreDTO {
    private Long movieId; // Corrigido de moiveId para movieId
    private String email;
    private Double score;

    public ScoreDTO() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}


@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    // Definindo o método saveScore
    @Transactional
    public MovieDTO saveScore(ScoreDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(dto.getMovieId()).orElseThrow();

        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());
        score = scoreRepository.saveAndFlush(score);

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


