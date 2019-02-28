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
import com.jegner.dnd.database.repo.RaceRepository;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.database.repo.TraitRepository;
import com.jegner.dnd.database.repo.WeaponPropertyRepository;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.item.WeaponProperty;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.model.predefined.Race;
import com.jegner.dnd.model.predefined.Skill;
import com.jegner.dnd.model.predefined.Trait;

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

	@Autowired
	private TraitRepository traitRepo;

	@Autowired
	private RaceRepository raceRepo;

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
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Trait.json"), Trait[].class, traitRepo);

		// Depends on one
		// Skill (ability score)
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Skill.json"), Skill[].class, skillRepo);

		// Depends on many
		// Class (Trait, Attack, Language)
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Race.json"), Race[].class, raceRepo);

	}

	private <T> List<T> generatePredefineds(File file, Class<T[]> class1, JpaRepository<T, Long> repo)
			throws Exception {
		List<T> predefineds = Arrays.asList(mapper.readValue(file, class1));
		repo.saveAll(predefineds);
		return predefineds;
	}
}
