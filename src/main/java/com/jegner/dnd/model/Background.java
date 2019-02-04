package com.jegner.dnd.model;

import java.util.List;

import com.jegner.dnd.model.item.Item;

import lombok.Data;

@Data
public class Background {

	private Proficiency startingProficiencies;
	private List<Language> languages;
	private List<Item> startingItems;

	private static List<Background> backgrounds;
}
