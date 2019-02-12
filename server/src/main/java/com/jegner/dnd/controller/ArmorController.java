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

import com.jegner.dnd.database.repo.ArmorRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.item.Armor;

// TODO autogeneration of this controller for basic types, @RepositoryRestResource
@RestController
@RequestMapping("/" + ArmorController.ENTITY_PATH)
public class ArmorController {
	static final String ENTITY_PATH = "armor";

	@Autowired
	ArmorRepository armorRepo;

	@GetMapping("/{id}")
	public Armor getArmorById(@PathVariable long id) {
		return armorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Armor", "id", id));
	}

	@GetMapping("")
	public List<Armor> getAllArmors() {
		return armorRepo.findAll();
	}

	@PostMapping("")
	public Armor postArmor(@Valid @RequestBody Armor armor) {
		return armorRepo.save(armor);
	}

	@PutMapping("/{id}")
	public Armor updateArmor(@PathVariable long id, @Valid @RequestBody Armor armorDetails) {
		Armor armor = getArmorById(id);

		BeanUtils.copyProperties(armorDetails, armor);

		// Makes it such that the put json does not have to include the id
		armor.setId(id);
		return armorRepo.save(armor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteArmorById(@PathVariable long id) {
		armorRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllArmors() {
		armorRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
