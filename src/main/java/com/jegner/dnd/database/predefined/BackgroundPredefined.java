package com.jegner.dnd.database.predefined;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.jegner.dnd.model.predefined.Background;
import com.jegner.dnd.utility.GameEntity;

public class BackgroundPredefined {
	public static List<Background> predefinedBackgrounds() throws MalformedURLException {
		Background acolyte = new Background();
		// Game Entity
		GameEntity acolyteGE = new GameEntity();
		acolyteGE.setName("Acolyte");
		acolyteGE.setDescription(
				"You have spent your life in the service of a temple to a specific god or pantheon of gods.You act as an intermediary between the realm of the holy and the mortal world, performing sacred rites and offering sacrifices in order to conduct worshipers into the presence of the divine.You are not necessarily a cleric; performing sacred rites is not the same thing as channeling divine power. Choose a god, a pantheon of gods, or some other quasi-divine being, and work with your DM to detail the nature of your religious service.");
		acolyteGE.setReferenceURL(new URL("https://dnd5e.fandom.com/wiki/Acolyte"));
		acolyte.setGameEntity(acolyteGE);
		// Languages
		// Tool & Skill proficiencies
		// Items

		return Arrays.asList(acolyte);
	}

}
