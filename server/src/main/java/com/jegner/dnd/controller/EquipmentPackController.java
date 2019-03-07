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

import com.jegner.dnd.database.repo.EquipmentPackRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.item.EquipmentPack;

@RestController
@RequestMapping("/" + EquipmentPackController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class EquipmentPackController {
	static final String ENTITY_PATH = "equipmentPack";

	@Autowired
	EquipmentPackRepository equipmentPackRepo;

	@GetMapping("/{id}")
	public EquipmentPack getEquipmentPackById(@PathVariable long id) {
		return equipmentPackRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("EquipmentPack", "id", id));
	}

	@GetMapping("")
	public List<EquipmentPack> getAllGameEntities() {
		return equipmentPackRepo.findAll();
	}

	@PostMapping("")
	public EquipmentPack postEquipmentPack(@Valid @RequestBody EquipmentPack equipmentPack) {
		return equipmentPackRepo.save(equipmentPack);
	}

	@PutMapping("/{id}")
	public EquipmentPack updateEquipmentPack(@PathVariable long id,
			@Valid @RequestBody EquipmentPack equipmentPackDetails) {
		EquipmentPack equipmentPack = getEquipmentPackById(id);

		BeanUtils.copyProperties(equipmentPackDetails, equipmentPack);

		// Makes it such that the put json does not have to include the id
		equipmentPack.setId(id);
		return equipmentPackRepo.save(equipmentPack);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEquipmentPackById(@PathVariable long id) {
		equipmentPackRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		equipmentPackRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
