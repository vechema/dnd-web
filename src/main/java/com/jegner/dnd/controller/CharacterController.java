package com.jegner.dnd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jegner.dnd.model.Character;

@RestController
public class CharacterController {

	@RequestMapping("/char")
	public Character index() {
		return new Character();
	}
}
