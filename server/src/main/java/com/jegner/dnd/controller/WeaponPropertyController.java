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

import com.jegner.dnd.database.repo.WeaponPropertyRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.item.WeaponProperty;

// TODO autogeneration of this controller for basic types, @RepositoryRestResource
@RestController
@RequestMapping("/" + WeaponPropertyController.ENTITY_PATH)
public class WeaponPropertyController {
	static final String ENTITY_PATH = "weaponProperty";

	@Autowired
	WeaponPropertyRepository weaponPropertyRepo;

	@GetMapping("/{id}")
	public WeaponProperty getweaponPropertyById(@PathVariable long id) {
		return weaponPropertyRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("WeaponProperty", "id", id));
	}

	@GetMapping("")
	public List<WeaponProperty> getAllweaponPropertys() {
		return weaponPropertyRepo.findAll();
	}

	@PostMapping("")
	public WeaponProperty postweaponProperty(@Valid @RequestBody WeaponProperty weaponProperty) {
		return weaponPropertyRepo.save(weaponProperty);
	}

	@PutMapping("/{id}")
	public WeaponProperty updateweaponProperty(@PathVariable long id,
			@Valid @RequestBody WeaponProperty weaponPropertyDetails) {
		WeaponProperty weaponProperty = getweaponPropertyById(id);

		BeanUtils.copyProperties(weaponPropertyDetails, weaponProperty);

		// Makes it such that the put json does not have to include the id
		weaponProperty.setId(id);
		return weaponPropertyRepo.save(weaponProperty);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteweaponPropertyById(@PathVariable long id) {
		weaponPropertyRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllweaponPropertys() {
		weaponPropertyRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
