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

import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.predefined.Skill;

@RestController
@RequestMapping("/" + SkillController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController {
	static final String ENTITY_PATH = "skill";

	@Autowired
	SkillRepository skillRepo;

	@GetMapping("/{id}")
	public Skill getSkillById(@PathVariable long id) {
		return skillRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skill", "id", id));
	}

	@GetMapping("")
	public List<Skill> getAllGameEntities() {
		return skillRepo.findAll();
	}

	@PostMapping("")
	public Skill postSkill(@Valid @RequestBody Skill skill) {
		return skillRepo.save(skill);
	}

	@PutMapping("/{id}")
	public Skill updateSkill(@PathVariable long id, @Valid @RequestBody Skill skillDetails) {
		Skill skill = getSkillById(id);

		BeanUtils.copyProperties(skillDetails, skill);

		// Makes it such that the put json does not have to include the id
		skill.setId(id);
		return skillRepo.save(skill);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSkillById(@PathVariable long id) {
		skillRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		skillRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
