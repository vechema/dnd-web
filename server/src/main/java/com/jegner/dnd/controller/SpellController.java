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

import com.jegner.dnd.database.repo.SpellRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.magic.Spell;

@RestController
@RequestMapping("/" + SpellController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class SpellController {
	static final String ENTITY_PATH = "spell";

	@Autowired
	SpellRepository spellRepo;

	@GetMapping("/{id}")
	public Spell getSpellById(@PathVariable long id) {
		return spellRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Spell", "id", id));
	}

	@GetMapping("")
	public List<Spell> getAllGameEntities() {
		return spellRepo.findAll();
	}

	@PostMapping("")
	public Spell postSpell(@Valid @RequestBody Spell spell) {
		return spellRepo.save(spell);
	}

	@PutMapping("/{id}")
	public Spell updateSpell(@PathVariable long id, @Valid @RequestBody Spell spellDetails) {
		Spell spell = getSpellById(id);

		BeanUtils.copyProperties(spellDetails, spell);

		// Makes it such that the put json does not have to include the id
		spell.setId(id);
		return spellRepo.save(spell);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSpellById(@PathVariable long id) {
		spellRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		spellRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
