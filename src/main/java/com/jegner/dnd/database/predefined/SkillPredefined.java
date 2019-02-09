package com.jegner.dnd.database.predefined;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.predefined.Skill;
import com.jegner.dnd.utility.GameEntity;

@Component
public class SkillPredefined implements PredefinedMaker<Skill> {

	@Autowired
	SkillRepository skillRepo;

	@Override
	public List<Skill> generatePredefineds() throws MalformedURLException {

		List<Skill> skills = new ArrayList<>();

		Skill acrobatics = new Skill();
		// Game Entity
		GameEntity acrobaticsGE = new GameEntity();
		acrobaticsGE.setName("Acrobatics");
		acrobaticsGE.setDescription(
				"Acrobatics is a skill used when trying to maintain balance or general nimbleness. Examples include walking across a thin or rickety bridge, running along a slippery surface, or moving out of the way of a projectile. It can also be used to break free of a grapple.");
		acrobaticsGE.setReferenceURL(new URL("https://dnd5e.fandom.com/wiki/Skills#Acrobatics"));
		acrobatics.setGameEntity(acrobaticsGE);
		// Ability Score/modifier
		acrobatics.setAbilityModifier(AbilityScore.DEXTERITY);
		skills.add(acrobatics);

		skillRepo.saveAll(skills);
		return Arrays.asList(acrobatics);
	}

}
