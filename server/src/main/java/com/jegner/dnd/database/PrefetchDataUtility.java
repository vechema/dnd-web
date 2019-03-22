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
import com.jegner.dnd.database.repo.ContainerRepository;
import com.jegner.dnd.database.repo.EquipmentPackRepository;
import com.jegner.dnd.database.repo.ItemRepository;
import com.jegner.dnd.database.repo.LanguageRepository;
import com.jegner.dnd.database.repo.RaceRepository;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.database.repo.SpellRepository;
import com.jegner.dnd.database.repo.TraitRepository;
import com.jegner.dnd.database.repo.WeaponPropertyRepository;
import com.jegner.dnd.database.repo.WeaponRepository;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.item.Container;
import com.jegner.dnd.model.item.EquipmentPack;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.model.item.Weapon;
import com.jegner.dnd.model.item.WeaponProperty;
import com.jegner.dnd.model.magic.Spell;
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

	@Autowired
	private ContainerRepository containerRepo;

	@Autowired
	private EquipmentPackRepository equipmentPackRepo;

	@Autowired
	private SpellRepository spellRepo;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String PREDEFINED_JSON_PATH = "src\\main\\resources\\database\\";

	public void initializeAllData() throws Exception {

		// TODO consider some sort of self registration, common interface, and iterate
		// through which each json is read and saved. Will have to consider order due to
		// dependencies

		// Depends on no one - Feat, Feature, Trait, Armor, Container, Item,
		// WeaponProperty, Language

		// Ability score
		List<AbilityScore> abilityScores = generatePredefineds(new File(PREDEFINED_JSON_PATH + "AbilityScore.json"),
				AbilityScore[].class, abilityScoreRepo);
		AbilityScore.setAbilityScores(abilityScores);

		// Armor
		List<Armor> armors = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Armor.json"), Armor[].class,
				armorRepo);
		Armor.setArmors(armors);
		Item.addItems(armors);

		// Weapon Property
		List<WeaponProperty> weaponProperties = generatePredefineds(
				new File(PREDEFINED_JSON_PATH + "WeaponProperty.json"), WeaponProperty[].class, weaponPropRepo);
		WeaponProperty.setWeaponProperties(weaponProperties);

		// Traits, ones common between classes/races like Darkvision
		// Ones unique to classes/races will be defined in that class/race
		List<Trait> commonTraits = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Trait.json"), Trait[].class,
				traitRepo);
		Trait.setCommonTraits(commonTraits);

		// Language
		List<Language> languages = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Language.json"),
				Language[].class, languageRepo);
		Language.setLanguages(languages);

		// Items - adventuring gear that is not armor, weapon, container, or equipment
		// pack
		List<Item> items = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Item.json"), Item[].class, itemRepo);
		Item.addItems(items);

		// Container
		List<Container> containers = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Container.json"),
				Container[].class, containerRepo);
		Container.setContainers(containers);
		Item.addItems(containers);

		// Equipment Pack
		List<EquipmentPack> equipmentPack = generatePredefineds(new File(PREDEFINED_JSON_PATH + "EquipmentPack.json"),
				EquipmentPack[].class, equipmentPackRepo);
		Item.addItems(equipmentPack);

		// Depends on one
		// Skill (ability score)
		List<Skill> skills = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Skill.json"), Skill[].class,
				skillRepo);
		Skill.setSkills(skills);

		// Weapon (weapon property)
		List<Weapon> weapons = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Weapon.json"), Weapon[].class,
				weaponRepo);
		Weapon.setWeapons(weapons);
		Item.addItems(weapons);

		// Depends on many
		// Spell (Item, Ability Score)
		List<Spell> spells = generatePredefineds(new File(PREDEFINED_JSON_PATH + "Spell.json"), Spell[].class,
				spellRepo);
		Spell.setSpells(spells);
		// Race (Trait, Weapon, Language, Ability Score)
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Race.json"), Race[].class, raceRepo);
		// Classs (Ability Score, Skill, Weapon, Item)
		generatePredefineds(new File(PREDEFINED_JSON_PATH + "Classs.json"), Classs[].class, classsRepo);

	}

	private <T> List<T> generatePredefineds(File file, Class<T[]> class1, JpaRepository<T, Long> repo)
			throws Exception {
		List<T> predefineds = Arrays.asList(mapper.readValue(file, class1));
		repo.saveAll(predefineds);
		return predefineds;
	}
}
