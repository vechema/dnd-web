package com.jegner.dnd.database;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jegner.dnd.database.repo.AbilityScoreRepo;
import com.jegner.dnd.database.repo.ArmorRepository;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.database.repo.WeaponPropertyRepository;
import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.item.WeaponProperty;
import com.jegner.dnd.model.predefined.Skill;

@Component
public class PrefetchDataUtility {

	@Autowired
	private SkillRepository skillRepo;

	@Autowired
	private ArmorRepository armorRepo;

	@Autowired
	private AbilityScoreRepo abilityScoreRepo;

	@Autowired
	private WeaponPropertyRepository weaponPropRepo;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String PREDEFINED_JSON_PATH = "src\\main\\resources\\database\\";

	public void initializeAllData() throws Exception {

		// TODO consider some sort of self registration, common interface, and iterate
		// through. Will have to consider order due to dependencies

		// Depends on no one - Feat, Feature, Trait, Armor, Container, Item,
		// WeaponProperty
		List<AbilityScore> abilityScores = generatePredefineds(new File(PREDEFINED_JSON_PATH + "AbilityScore.json"),
				AbilityScore[].class, abilityScoreRepo);
		AbilityScore.setAbilityScores(abilityScores);
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Armor.json"), Armor[].class, armorRepo);
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "WeaponProperty.json"), WeaponProperty[].class,
				weaponPropRepo);

		// Depends on one
		// Skill (ability score)
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Skill.json"), Skill[].class, skillRepo);

	}

	private <T> List<T> generatePredefineds(File file, Class<T[]> class1, JpaRepository<T, Long> repo)
			throws Exception {
		List<T> predefineds = Arrays.asList(mapper.readValue(file, class1));
		repo.saveAll(predefineds);
		return predefineds;
	}
}
