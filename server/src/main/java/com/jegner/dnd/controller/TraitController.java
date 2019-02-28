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

import com.jegner.dnd.database.repo.TraitRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.predefined.Trait;

@RestController
@RequestMapping("/" + TraitController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class TraitController {
	static final String ENTITY_PATH = "trait";

	@Autowired
	TraitRepository traitRepo;

	@GetMapping("/{id}")
	public Trait getTraitById(@PathVariable long id) {
		return traitRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trait", "id", id));
	}

	@GetMapping("")
	public List<Trait> getAllGameEntities() {
		return traitRepo.findAll();
	}

	@PostMapping("")
	public Trait postTrait(@Valid @RequestBody Trait trait) {
		return traitRepo.save(trait);
	}

	@PutMapping("/{id}")
	public Trait updateTrait(@PathVariable long id, @Valid @RequestBody Trait traitDetails) {
		Trait trait = getTraitById(id);

		BeanUtils.copyProperties(traitDetails, trait);

		// Makes it such that the put json does not have to include the id
		trait.setId(id);
		return traitRepo.save(trait);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTraitById(@PathVariable long id) {
		traitRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		traitRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
