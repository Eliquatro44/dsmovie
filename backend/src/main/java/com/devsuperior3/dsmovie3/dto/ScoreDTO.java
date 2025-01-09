package com.devsuperior3.dsmovie3.dto;

public class ScoreDTO { // Nome corrigido
    private Long movieId;
    private String email;
    private Double score;

    public ScoreDTO() { }

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


/*
public class ScoreDT {
    private Long movieId; // Corrigido de moiveId para movieId
    private String email;
    private Double score;

    public ScoreDT() {
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
 */