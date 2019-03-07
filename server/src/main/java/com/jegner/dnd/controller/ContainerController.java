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

import com.jegner.dnd.database.repo.ContainerRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.item.Container;

@RestController
@RequestMapping("/" + ContainerController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class ContainerController {
	static final String ENTITY_PATH = "container";

	@Autowired
	ContainerRepository containerRepo;

	@GetMapping("/{id}")
	public Container getContainerById(@PathVariable long id) {
		return containerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Container", "id", id));
	}

	@GetMapping("")
	public List<Container> getAllGameEntities() {
		return containerRepo.findAll();
	}

	@PostMapping("")
	public Container postContainer(@Valid @RequestBody Container container) {
		return containerRepo.save(container);
	}

	@PutMapping("/{id}")
	public Container updateContainer(@PathVariable long id, @Valid @RequestBody Container containerDetails) {
		Container container = getContainerById(id);

		BeanUtils.copyProperties(containerDetails, container);

		// Makes it such that the put json does not have to include the id
		container.setId(id);
		return containerRepo.save(container);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteContainerById(@PathVariable long id) {
		containerRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		containerRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
