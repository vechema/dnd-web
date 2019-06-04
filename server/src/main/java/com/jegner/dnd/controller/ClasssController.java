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

import com.jegner.dnd.database.repo.ClasssRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.predefined.Classs;

@RestController
@RequestMapping("/" + ClasssController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class ClasssController {
	static final String ENTITY_PATH = "classs";

	@Autowired
	ClasssRepository classsRepo;

	@GetMapping("/{id}")
	public Classs getClasssById(@PathVariable long id) {
		return classsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Classs", "id", id));
	}

	@GetMapping("")
	public List<Classs> getAllGameEntities() {
		return classsRepo.findAll();
	}

	@PostMapping("")
	public Classs postClasss(@Valid @RequestBody Classs classs) {
		return classsRepo.save(classs);
	}

	@PutMapping("/{id}")
	public Classs updateClasss(@PathVariable long id, @Valid @RequestBody Classs classsDetails) {
		Classs classs = getClasssById(id);

		BeanUtils.copyProperties(classsDetails, classs);

		// Makes it such that the put json does not have to include the id
		classs.setId(id);
		return classsRepo.save(classs);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClasssById(@PathVariable long id) {
		classsRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		classsRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
