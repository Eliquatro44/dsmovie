package com.devsuperior3.dsmovie3.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ScorePK implements Serializable {

    private static final long serialVersionUID = 1L; // Adicionado serialVersionUID

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // hashCode and equals
    @Override
    public int hashCode() {
        return Objects.hash(movie, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ScorePK other = (ScorePK) obj;
        return Objects.equals(movie, other.movie) && Objects.equals(user, other.user);
    }
}


