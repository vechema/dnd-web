package com.jegner.dnd.database.predefined;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.predefined.Skill;
import com.jegner.dnd.utility.GameEntity;

public class SkillPredefined {
	public static List<Skill> predefinedSkills() throws MalformedURLException {
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

		return Arrays.asList(acrobatics);
	}

}
