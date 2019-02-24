package com.jegner.dnd.model.modify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
		AbilityScore dex = new AbilityScore();
		int dexScore = 12;

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(CharacterAbility.calculateModifier(dexScore));

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Give the dex to the player
		character.addAbilityScore(dex, dexScore);

		// Create Armor
		Armor armor = new Armor();

		// What is armor modified by? Dex in this case
		Modify armorModify = new Modify();
		armorModify.setBase(14);
		armorModify.setModifyField(ModifyField.ARMOR_AC);
		armorModify.setFieldsIModify(Arrays.asList(ModifyField.CHARACTER_AC));
		armorModify.setFieldsThatModifyMe(Arrays.asList(ModifyField.DEXTERITY_MOD));

		// Wrap it up together
		GameEntity armorGameEntity = new GameEntity();
		armorGameEntity.setName("Ring Mail");
		armorGameEntity.setModify(armorModify);
		armor.setGameEntity(armorGameEntity);

		// Put armor in character inventory & equip
		character.addItem(armor);
		character.equip(armor);

		// Get Character AC
		int ac = character.getAC();
		System.out.println(ac);

		assertThat(ac, is(armor.getGameEntity().getModify().getBase() + CharacterAbility.calculateModifier(dexScore)));

	}
}
