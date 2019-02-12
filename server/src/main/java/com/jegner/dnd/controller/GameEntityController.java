package com.jegner.dnd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jegner.dnd.database.repo.GameEntityRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.utility.GameEntity;

// TODO autogeneration of this controller for basic types, @RepositoryRestResource
@RestController
@RequestMapping("/" + GameEntityController.ENTITY_PATH)
public class GameEntityController {
	static final String ENTITY_PATH = "entity";

	@Autowired
	GameEntityRepository gameEntityRepo;

	@GetMapping("/{id}")
	public GameEntity getGameEntityById(@PathVariable long id) {
		return gameEntityRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game Entity", "id", id));
	}

	@GetMapping("")
	public List<GameEntity> getAllGameEntities() {
		return gameEntityRepo.findAll();
	}

	@PostMapping("")
	public GameEntity postGameEntity(@Valid @RequestBody GameEntity gameEntity) {
		return gameEntityRepo.save(gameEntity);
	}

	@PutMapping("/{id}")
	public GameEntity updateGameEntity(@PathVariable long id, @Valid @RequestBody GameEntity gameEntityDetails) {
		GameEntity gameEntity = getGameEntityById(id);

		BeanUtils.copyProperties(gameEntityDetails, gameEntity);

		// Makes it such that the put json does not have to include the id
		gameEntity.setId(id);
		return gameEntityRepo.save(gameEntity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGameEntityById(@PathVariable long id) {
		gameEntityRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		gameEntityRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
