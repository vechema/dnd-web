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

import com.jegner.dnd.database.repo.WeaponRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.item.Weapon;

@RestController
@RequestMapping("/" + WeaponController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class WeaponController {
	static final String ENTITY_PATH = "weapon";

	@Autowired
	WeaponRepository weaponRepo;

	@GetMapping("/{id}")
	public Weapon getWeaponById(@PathVariable long id) {
		return weaponRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Weapon", "id", id));
	}

	@GetMapping("")
	public List<Weapon> getAllGameEntities() {
		return weaponRepo.findAll();
	}

	@PostMapping("")
	public Weapon postWeapon(@Valid @RequestBody Weapon weapon) {
		return weaponRepo.save(weapon);
	}

	@PutMapping("/{id}")
	public Weapon updateWeapon(@PathVariable long id, @Valid @RequestBody Weapon weaponDetails) {
		Weapon weapon = getWeaponById(id);

		BeanUtils.copyProperties(weaponDetails, weapon);

		// Makes it such that the put json does not have to include the id
		weapon.setId(id);
		return weaponRepo.save(weapon);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteWeaponById(@PathVariable long id) {
		weaponRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		weaponRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
