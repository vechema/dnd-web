package com.jegner.dnd.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jegner.dnd.model.Character;
import com.jegner.dnd.model.Experience;
import com.jegner.dnd.model.modify.Modify;
import com.jegner.dnd.model.modify.ModifyField;
import com.jegner.dnd.model.predefined.Classs;
import com.jegner.dnd.model.predefined.Level;
import com.jegner.dnd.model.predefined.LevelingTable;
import com.jegner.dnd.model.predefined.Race;
import com.jegner.dnd.utility.GameEntity;

@RestController
public class CharacterController {

	@RequestMapping("/char")
	public Character newCharacter() {
		Character newChar = new Character();
		newChar.setName("Christine");
		// get a Classs
		newChar.setClasss(makeClass());
		// get Race
		newChar.setRace(makeRace());

		// TODO Make experience class when creating character!
		// set Experience
		Experience xp = new Experience();
		xp.setExperiencePoints(0);
		newChar.setExperience(xp);

		return newChar;
	}

	private Classs makeClass() {
		int profBonus = 1;
		Modify profBonusModify = new Modify();
		profBonusModify.setBase(profBonus);
		profBonusModify.setModifyField(ModifyField.PROFICIENCY);
		profBonusModify.setFieldIModify(ModifyField.ATTACK_HIT);

		Level level = new Level();
		level.setProficiencyBonus(profBonusModify);
		level.setLevel(1);

		LevelingTable levelingTable = new LevelingTable();
		levelingTable.setLevels(Arrays.asList(level));

		Classs classs = new Classs();
		classs.setLevelingTable(levelingTable);
		GameEntity classGameEntity = new GameEntity();
		classGameEntity.setName("Sorceror");
		classs.setGameEntity(classGameEntity);

		// hit dice
		classs.setHitDice(8);

		return classs;
	}

	private Race makeRace() {

		Race race = new Race();

		GameEntity raceGameEntity = new GameEntity();
		raceGameEntity.setName("Tiefling");
		race.setGameEntity(raceGameEntity);

		race.setStartingSpeed(30);

		return race;
	}

	@RequestMapping("/")
	public String index() {
		StringBuilder result = new StringBuilder();
		result.append("All endpoints support GET, PUT, POST, DELETE by ID as well as GET and DELETE all");
		result.append("<ul>");
		result.append("<li><a href=\"/armor\">Armors</a></li>");
		result.append("<li><a href=\"/char\">Sample Character</a></li>");
		result.append("<li><a href=\"/classs\">Classes</a></li>");
		result.append("<li><a href=\"/container\">Items that are containers</a></li>");
		result.append("<li><a href=\"/equipmentPack\">Equipment Pack items</a></li>");
		result.append("<li><a href=\"/entity\">Game Entities, not really useful by itself</a></li>");
		result.append("<li><a href=\"/item\">ALL items</a></li>");
		result.append("<li><a href=\"/language\">Languages</a></li>");
		result.append("<li><a href=\"/race\">Races</a></li>");
		result.append("<li><a href=\"/skill\">Skills</a></li>");
		result.append("<li><a href=\"/spell\">Spells</a></li>");
		result.append("<li><a href=\"/trait\">Traits</a></li>");
		result.append("<li><a href=\"/weapon\">Weapons</a></li>");
		result.append("<li><a href=\"/weaponProperty\">Weapon Properties, not really useful by itself</a></li>");
		result.append("</ul>");
		return result.toString();
	}
}
