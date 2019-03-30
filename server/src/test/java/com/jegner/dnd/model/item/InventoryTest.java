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

	@Test
	public void testWeaponEquiped() {
		// Create character
		Character character = new Character();

		// Check no armor equipped
		boolean weaponEquipped = character.isWeaponEquipped();
		assertThat(weaponEquipped, is(false));

		// Create weapon
		GameEntity clubGameEntity = new GameEntity();
		clubGameEntity.setName("Club");
		Weapon club = new Weapon();
		club.setGameEntity(clubGameEntity);

		// Equip weapon
		character.addItem(club);
		character.equip(club);

		weaponEquipped = character.isWeaponEquipped();
		assertThat(weaponEquipped, is(true));

		// Unequip weapon
		character.unequip(club);
		weaponEquipped = character.isWeaponEquipped();
		assertThat(weaponEquipped, is(false));
	}

	@Test
	public void autoUnequipTest() {
		// Create character
		Character character = new Character();

		// Create weapons
		GameEntity maceGameEntity = new GameEntity();
		maceGameEntity.setName("Mace");
		Weapon mace = new Weapon();
		mace.setGameEntity(maceGameEntity);
		GameEntity clubGameEntity = new GameEntity();
		clubGameEntity.setName("Club");
		Weapon club = new Weapon();
		club.setGameEntity(clubGameEntity);

		// Create armors
		GameEntity splintGameEntity = new GameEntity();
		splintGameEntity.setName("Splint");
		Armor splintArmor = new Armor();
		splintArmor.setGameEntity(splintGameEntity);
		GameEntity plateArmorGameEntity = new GameEntity();
		plateArmorGameEntity.setName("Plate");
		Armor plateArmor = new Armor();
		plateArmor.setGameEntity(plateArmorGameEntity);

		// Add to character
		character.addItem(plateArmor);
		character.addItem(splintArmor);
		character.addItem(club);
		character.addItem(mace);

		// Equip armor and weapon
		character.equip(plateArmor);
		character.equip(club);

		assertThat(character.getEquippedArmor(), is(plateArmor));
		assertThat(character.getWeapon(), is(club));

		// Equip other armor and weapon
		character.equip(splintArmor);
		character.equip(mace);

		assertThat(character.getEquippedArmor(), is(splintArmor));
		assertThat(character.getWeapon(), is(mace));

	}
}
