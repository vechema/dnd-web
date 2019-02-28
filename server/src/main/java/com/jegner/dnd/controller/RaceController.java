package com.jegner.dnd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jegner.dnd.database.repo.RaceRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.predefined.Race;

@RestController
@RequestMapping("/" + RaceController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class RaceController {
	static final String ENTITY_PATH = "race";

	@Autowired
	RaceRepository raceRepo;

	@GetMapping("/{id}")
	public Race getRaceById(@PathVariable long id) {
		return raceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Race", "id", id));
	}

	@GetMapping("")
	public List<Race> getAllGameEntities() {
		return raceRepo.findAll();
	}

	@PostMapping("")
	public Race postRace(@Valid @RequestBody Race race) {
		return raceRepo.save(race);
	}

	@PutMapping("/{id}")
	public Race updateRace(@PathVariable long id, @Valid @RequestBody Race raceDetails) {
		Race race = getRaceById(id);

		BeanUtils.copyProperties(raceDetails, race);

		// Makes it such that the put json does not have to include the id
		race.setId(id);
		return raceRepo.save(race);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRaceById(@PathVariable long id) {
		raceRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		raceRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
