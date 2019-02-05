package com.jegner.dnd.database;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jegner.dnd.database.predefined.BackgroundPredefined;
import com.jegner.dnd.database.predefined.SkillPredefined;
import com.jegner.dnd.database.repo.BackgroundRepository;
import com.jegner.dnd.database.repo.GameEntityRepository;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.model.predefined.Background;
import com.jegner.dnd.utility.GameEntity;

@Component
public class PrefetchDataUtility {

	@Autowired
	GameEntityRepository gameEntityRepo;

	@Autowired
	BackgroundRepository backgroundRepo;

	@Autowired
	SkillRepository skillRepo;

	public void initializeAllData() throws MalformedURLException {
		gameEntityRepo.save(new GameEntity());
		backgroundRepo.save(new Background());
		// TODO consider some sort of self registration, common interface, and iterate
		// through. Will have to consider order due to dependencies
		// Depends on no one - Skill, Feat, Feature, Trait, Armor, Container, Item,
		// WeaponProperty
		skillRepo.saveAll(SkillPredefined.predefinedSkills());

		backgroundRepo.saveAll(BackgroundPredefined.predefinedBackgrounds());

	}
}
