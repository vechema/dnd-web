package com.jegner.dnd.model.modify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.jegner.dnd.model.AbilityScore;
import com.jegner.dnd.model.Character;
import com.jegner.dnd.model.CharacterAbility;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.item.Inventory;
import com.jegner.dnd.utility.GameEntity;

public class CharacterModifySystemTest {

	@Test
	public void armorTest() {

		// Create Character
		Character character = new Character();
		Inventory inventory = new Inventory();
		character.setInventory(inventory);

		// Set AbilityScore
		CharacterAbility charAbility = new CharacterAbility();
		AbilityScore dex = new AbilityScore();
		dex.setValue(12);
		// Dex modifier
		Modifier dexMod = new Modifier();
		dexMod.setModifierField(ModifierField.DEXTERITY_MOD);
		Map<ModifiedField, Integer> dexModifierFieldsMap = new HashMap<>();
		dexModifierFieldsMap.put(ModifiedField.ARMOR_AC, dex.calculateModifier());
		dexMod.setModifierFields(dexModifierFieldsMap);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModifier(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Create Armor
		Armor armor = new Armor();

		// What is armor modified by? Dex in this case
		Modified armorModifiedByDexMod = new Modified();
		armorModifiedByDexMod.setBase(14);
		armorModifiedByDexMod.setModifiedField(ModifiedField.ARMOR_AC);
		armorModifiedByDexMod.setModifiers(Arrays.asList(dexMod));

		// What is it modifying? Character AC
		Modifier armorCharAcModifier = new Modifier();
		armorCharAcModifier.setModifierField(ModifierField.ARMOR_AC);
		Map<ModifiedField, Integer> armorModMap = new HashMap<>();
		armorModMap.put(ModifiedField.CHARACTER_AC, armorModifiedByDexMod.getModAmount());
		armorCharAcModifier.setModifierFields(armorModMap);

		// Wrap it up together
		GameEntity armorGameEntity = new GameEntity();
		armorGameEntity.setName("Ring Mail");
		armorGameEntity.setModified(armorModifiedByDexMod);
		armorGameEntity.setModifier(armorCharAcModifier);
		armor.setGameEntity(armorGameEntity);

		// Put armor in character inventory & equip
		character.addItem(armor);
		character.equip(armor);

		// Get Character AC
		int ac = character.getAC();

		System.out.println(ac);

	}
}
