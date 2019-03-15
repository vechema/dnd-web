package com.jegner.dnd.model.item;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import com.jegner.dnd.model.Character;
import com.jegner.dnd.utility.GameEntity;

public class InventoryTest {

	@Test
	public void testArmorEquiped() {
		// Create character
		Character character = new Character();

		// Check no armor equipped
		boolean armorEquipped = character.isArmorEquipped();
		assertThat(armorEquipped, is(false));

		// Create armor
		GameEntity plateArmorGameEntity = new GameEntity();
		plateArmorGameEntity.setName("Plate");
		Armor plateArmor = new Armor();
		plateArmor.setGameEntity(plateArmorGameEntity);

		// Equip armor
		character.addItem(plateArmor);
		character.equip(plateArmor);

		armorEquipped = character.isArmorEquipped();
		assertThat(armorEquipped, is(true));

		// Unequip Armor
		character.unequip(plateArmor);
		armorEquipped = character.isArmorEquipped();
		assertThat(armorEquipped, is(false));
	}
}
