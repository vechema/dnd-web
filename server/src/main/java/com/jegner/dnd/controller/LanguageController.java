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

import com.jegner.dnd.database.repo.LanguageRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.predefined.Language;

@RestController
@RequestMapping("/" + LanguageController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class LanguageController {
	static final String ENTITY_PATH = "language";

	@Autowired
	LanguageRepository languageRepo;

	@GetMapping("/{id}")
	public Language getLanguageById(@PathVariable long id) {
		return languageRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Language", "id", id));
	}

	@GetMapping("")
	public List<Language> getAllGameEntities() {
		return languageRepo.findAll();
	}

	@PostMapping("")
	public Language postLanguage(@Valid @RequestBody Language language) {
		return languageRepo.save(language);
	}

	@PutMapping("/{id}")
	public Language updateLanguage(@PathVariable long id, @Valid @RequestBody Language languageDetails) {
		Language language = getLanguageById(id);

		BeanUtils.copyProperties(languageDetails, language);

		// Makes it such that the put json does not have to include the id
		language.setId(id);
		return languageRepo.save(language);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLanguageById(@PathVariable long id) {
		languageRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		languageRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
