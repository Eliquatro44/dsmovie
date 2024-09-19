package com.devsuperior3.dsmovie3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior3.dsmovie3.dto.MovieDTO;
import com.devsuperior3.dsmovie3.dto.ScoreDTO; // Importação da classe ScoreDTO
import com.devsuperior3.dsmovie3.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController { // Corrigido o nome da classe
	
    @Autowired
    private MovieService service;

    @GetMapping
    public Page<MovieDTO> findAll(Pageable pageable) { // Método para listar todos os filmes
        return service.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public MovieDTO findById(@PathVariable Long id) { // Método para buscar filme por ID
        return service.findById(id);
    }

    @PutMapping // Pode ser alterado para @PostMapping se for mais apropriado
    public MovieDTO saveScore(@RequestBody ScoreDTO dto) {
        return service.saveScore(dto);
    }
}
