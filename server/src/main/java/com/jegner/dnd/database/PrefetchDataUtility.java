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
import com.jegner.dnd.database.repo.ClasssRepository;
import com.jegner.dnd.database.repo.ItemRepository;
import com.jegner.dnd.database.repo.LanguageRepository;
import com.jegner.dnd.database.repo.RaceRepository;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.database.repo.TraitRepository;
import com.jegner.dnd.database.repo.WeaponPropertyRepository;
import com.jegner.dnd.database.repo.WeaponRepository;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Weapon;
import com.jegner.dnd.model.item.WeaponProperty;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.model.predefined.Classs;
import com.jegner.dnd.model.predefined.Language;
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

	@Autowired
	private LanguageRepository languageRepo;

	@Autowired
	private WeaponRepository weaponRepo;

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private ClasssRepository classsRepo;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String PREDEFINED_JSON_PATH = "src\\main\\resources\\database\\";

	public void initializeAllData() throws Exception {

		// TODO consider some sort of self registration, common interface, and iterate
		// through. Will have to consider order due to dependencies

		// Depends on no one - Feat, Feature, Trait, Armor, Container, Item,
		// WeaponProperty, Language
		List<AbilityScore> abilityScores = generatePredefineds(new File(PREDEFINED_JSON_PATH + "AbilityScore.json"),
				AbilityScore[].class, abilityScoreRepo);
		AbilityScore.setAbilityScores(abilityScores);

		List<Armor> armors = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Armor.json"), Armor[].class,
				armorRepo);
		Armor.setArmors(armors);
		Item.addItems(armors);

		List<WeaponProperty> weaponProperties = generatePredefineds(
				new File(PREDEFINED_JSON_PATH + "WeaponProperty.json"), WeaponProperty[].class, weaponPropRepo);
		WeaponProperty.setWeaponProperties(weaponProperties);

		List<Trait> commonTraits = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Trait.json"), Trait[].class,
				traitRepo);
		Trait.setCommonTraits(commonTraits);

		List<Language> languages = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Language.json"),
				Language[].class, languageRepo);
		Language.setLanguages(languages);

		List<Item> items = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Item.json"), Item[].class, itemRepo);
		Item.addItems(items);

		// Depends on one
		// Skill (ability score)
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Skill.json"), Skill[].class, skillRepo);
		// Weapon (weapon property
		List<Weapon> weapons = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Weapon.json"), Weapon[].class,
				weaponRepo);
		Weapon.setWeapons(weapons);
		Item.addItems(weapons);

		// Depends on many
		// Race (Trait, Attack, Language)
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Race.json"), Race[].class, raceRepo);
		// Classs
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Classs.json"), Classs[].class, classsRepo);

	}

	private <T> List<T> generatePredefineds(File file, Class<T[]> class1, JpaRepository<T, Long> repo)
			throws Exception {
		List<T> predefineds = Arrays.asList(mapper.readValue(file, class1));
		repo.saveAll(predefineds);
		return predefineds;
	}
}
