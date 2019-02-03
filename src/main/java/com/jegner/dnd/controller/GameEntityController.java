package com.jegner.dnd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jegner.dnd.database.GameEntityRepository;
import com.jegner.dnd.utility.GameEntity;

@RestController
@RequestMapping("/entity")
public class GameEntityController {
	
	@Autowired
	GameEntityRepository gameEntityRepo;
	
	@RequestMapping("/{id}")
	public Optional<GameEntity> getById(@PathVariable long id) {
		return gameEntityRepo.findById(id);
	}
}
