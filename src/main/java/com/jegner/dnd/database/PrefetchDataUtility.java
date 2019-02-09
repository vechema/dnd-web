package com.jegner.dnd.database;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jegner.dnd.database.predefined.BackgroundPredefined;
import com.jegner.dnd.database.predefined.PredefinedMaker;
import com.jegner.dnd.database.predefined.SkillPredefined;
import com.jegner.dnd.database.repo.BackgroundRepository;
import com.jegner.dnd.database.repo.GameEntityRepository;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.model.predefined.Background;
import com.jegner.dnd.utility.GameEntity;

@Component
public class PrefetchDataUtility {

	@Autowired
	List<PredefinedMaker<?>> predefineds;

	public void initializeAllData() throws MalformedURLException {
		// TODO consider some sort of self registration, common interface, and iterate
		// through. Will have to consider order due to dependencies
		// Depends on no one - Skill, Feat, Feature, Trait, Armor, Container, Item,
		// WeaponProperty
		for (PredefinedMaker<?> predefined : predefineds) {
			predefined.generatePredefineds();
		}

	}
}
