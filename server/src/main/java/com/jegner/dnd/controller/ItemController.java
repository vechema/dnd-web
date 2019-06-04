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

import com.jegner.dnd.database.repo.ItemRepository;
import com.jegner.dnd.exception.ResourceNotFoundException;
import com.jegner.dnd.model.item.Item;

@RestController
@RequestMapping("/" + ItemController.ENTITY_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
	static final String ENTITY_PATH = "item";

	@Autowired
	ItemRepository itemRepo;

	@GetMapping("/{id}")
	public Item getItemById(@PathVariable long id) {
		return itemRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id", id));
	}

	@GetMapping("")
	public List<Item> getAllGameEntities() {
		return itemRepo.findAll();
	}

	@PostMapping("")
	public Item postItem(@Valid @RequestBody Item item) {
		return itemRepo.save(item);
	}

	@PutMapping("/{id}")
	public Item updateItem(@PathVariable long id, @Valid @RequestBody Item itemDetails) {
		Item item = getItemById(id);

		BeanUtils.copyProperties(itemDetails, item);

		// Makes it such that the put json does not have to include the id
		item.setId(id);
		return itemRepo.save(item);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItemById(@PathVariable long id) {
		itemRepo.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteAllGameEntities() {
		itemRepo.deleteAll();

		return ResponseEntity.ok().build();
	}
}
