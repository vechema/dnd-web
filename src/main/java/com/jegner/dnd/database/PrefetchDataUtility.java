package com.jegner.dnd.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jegner.dnd.database.predefined.PredefinedMaker;

@Component
public class PrefetchDataUtility {

	@Autowired
	List<PredefinedMaker<?>> predefineds;

	public void initializeAllData() throws Exception {
		// TODO consider some sort of self registration, common interface, and iterate
		// through. Will have to consider order due to dependencies
		// Depends on no one - Skill, Feat, Feature, Trait, Armor, Container, Item,
		// WeaponProperty
		for (PredefinedMaker<?> predefined : predefineds) {
			predefined.generatePredefineds();
		}

	}
}
