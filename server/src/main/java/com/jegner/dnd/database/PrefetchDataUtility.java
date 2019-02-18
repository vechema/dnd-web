package com.jegner.dnd.database;

import java.io.File;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jegner.dnd.database.repo.AbilityScoreRepo;
import com.jegner.dnd.database.repo.ArmorRepository;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.predefined.Skill;

@Component
public class PrefetchDataUtility {

	@Autowired
	SkillRepository skillRepo;

	@Autowired
	ArmorRepository armorRepo;

	@Autowired
	AbilityScoreRepo abilityScoreRepo;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String PREDEFINED_JSON_PATH = "src\\main\\resources\\database\\";

	public void initializeAllData() throws Exception {
		// TODO consider some sort of self registration, common interface, and iterate
		// through. Will have to consider order due to dependencies

		// Depends on no one - Skill, Feat, Feature, Trait, Armor, Container, Item,
		// WeaponProperty
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "AbilityScore.json"), AbilityScore[].class, abilityScoreRepo);
		//generatePredefineds(new File(PREDEFINED_JSON_PATH + "Skill.json"), Skill[].class, skillRepo);
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Armor.json"), Armor[].class, armorRepo);

	}

	private <T> void generatePredefineds(File file, Class<T[]> class1, JpaRepository<T, Long> repo) throws Exception {
		T[] predefineds = mapper.readValue(file, class1);
		repo.saveAll(Arrays.asList(predefineds));
	}
}
