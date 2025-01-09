package com.devsuperior3.dsmovie3.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior3.dsmovie3.dto.ScoreDTO;

/*
@RestController
// @RequestMapping(value = "/movies")
public class ScoreController {
	
	
	@PutMapping
	public MovieDTO saveScore(@RequestBody ScoreDTO dto) {
		return null;
	}
}
*/

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @PutMapping("/{id}")
    public ResponseEntity<Void> saveScore(@PathVariable Long id, @RequestBody ScoreDTO dto) {
        // Lógica aqui
        return ResponseEntity.noContent().build();
    }
}