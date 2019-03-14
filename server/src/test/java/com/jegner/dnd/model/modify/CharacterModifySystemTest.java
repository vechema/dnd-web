package com.jegner.dnd.model.modify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jegner.dnd.model.Character;
import com.jegner.dnd.model.CharacterAbility;
import com.jegner.dnd.model.item.Armor;
import com.jegner.dnd.model.item.Weapon;
import com.jegner.dnd.model.predefined.AbilityScore;
import com.jegner.dnd.model.predefined.Classs;
import com.jegner.dnd.model.predefined.Feature;
import com.jegner.dnd.model.predefined.Level;
import com.jegner.dnd.model.predefined.LevelingTable;
import com.jegner.dnd.utility.GameEntity;

public class CharacterModifySystemTest {

	@Test
	public void armorModifiedByDexModTest() {

		// Create Character
		Character character = new Character();

		// Set AbilityScore
		AbilityScore dex = new AbilityScore();
		int dexScore = 18; // 4
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

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
		armorModify.setFieldIModify(ModifyField.CHARACTER_AC);

		Map<ModifyField, Integer> fieldsThatModifyMeMap = new HashMap<>();
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, Integer.MAX_VALUE);
		armorModify.setFieldsThatModifyMe(fieldsThatModifyMeMap);

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

		int base = armor.getGameEntity().getModifys().stream().mapToInt(modify -> modify.getBase()).sum();
		assertThat(ac, is(base + dexModAmount));

		int dexModLimit = 2;
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, dexModLimit);
		ac = character.getAC();
		assertThat(ac, is(base + dexModLimit));
	}

	@Test
	public void armorModifiedByDexOrStrengthModTest() {

		// Create Character
		Character character = new Character();

		// Set AbilityScore
		AbilityScore dex = new AbilityScore();
		int dexScore = 16; // 3
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);
		AbilityScore strength = new AbilityScore();
		int strengthScore = 20; // 5
		int strengthModAmount = CharacterAbility.calculateModifier(strengthScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Strength modifier
		Modify strengthMod = new Modify();
		strengthMod.setModifyField(ModifyField.STRENGTH_MOD);
		strengthMod.setBase(strengthModAmount);

		GameEntity strengthGameEntity = new GameEntity();
		strengthGameEntity.setName("Strength");
		strengthGameEntity.setModify(strengthMod);
		strength.setGameEntity(strengthGameEntity);

		// Give the dex to the player
		character.addAbilityScore(dex, dexScore);
		character.addAbilityScore(strength, strengthScore);

		// Create Armor
		Armor armor = new Armor();

		// What is armor modified by? Dex in this case
		Modify armorModify = new Modify();
		armorModify.setBase(14);
		armorModify.setModifyField(ModifyField.ARMOR_AC);
		armorModify.setFieldIModify(ModifyField.CHARACTER_AC);

		Map<ModifyField, Integer> fieldsThatModifyMeMap = new HashMap<>();
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, Integer.MAX_VALUE);
		fieldsThatModifyMeMap.put(ModifyField.STRENGTH_MOD, Integer.MAX_VALUE);
		armorModify.setFieldsThatModifyMe(fieldsThatModifyMeMap);

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

		int base = armor.getGameEntity().getModifys().stream().mapToInt(modify -> modify.getBase()).sum();
		assertThat(ac, is(base + dexModAmount + strengthModAmount));

		int dexModLimit = 2;
		fieldsThatModifyMeMap.put(ModifyField.DEXTERITY_MOD, dexModLimit);
		ac = character.getAC();
		assertThat(ac, is(base + dexModLimit + strengthModAmount));

		armorModify.setModifyOperation(ModifyOperation.MAX);
		ac = character.getAC();
		assertThat(ac, is(base + Math.max(dexModLimit, strengthModAmount)));
	}

	@Test
	public void weaponFinesseProficiencyTest() {
		// Create Character
		Character character = new Character();

		// Set AbilityScore
		AbilityScore dex = new AbilityScore();
		int dexScore = 20; // 5, want larger so finesse makes us use this one
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);
		AbilityScore strength = new AbilityScore();
		int strengthScore = 16; // 3
		int strengthModAmount = CharacterAbility.calculateModifier(strengthScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Strength modifier
		Modify strengthMod = new Modify();
		strengthMod.setModifyField(ModifyField.STRENGTH_MOD);
		strengthMod.setBase(strengthModAmount);

		GameEntity strengthGameEntity = new GameEntity();
		strengthGameEntity.setName("Strength");
		strengthGameEntity.setModify(strengthMod);
		strength.setGameEntity(strengthGameEntity);

		// Give the dex to the player
		character.addAbilityScore(dex, dexScore);
		character.addAbilityScore(strength, strengthScore);

		// Set proficiency
		// This should normally be decided by character class -> Leveling Table -> Level
		// -> Modify
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
		classs.setGameEntity(new GameEntity());

		character.setClasss(classs);
		character.setCurrentLevel(1);

		// Create weapon
		Weapon weapon = new Weapon();
		GameEntity weaponGameEntity = new GameEntity();
		weaponGameEntity.setName("Sword");
		weapon.setGameEntity(weaponGameEntity);
		weaponGameEntity.addModify(strengthMod);

		// Set weapon magic
		int weaponMagicBonus = 2;
		Modify weaponMagicModify = new Modify();
		weaponMagicModify.setBase(weaponMagicBonus);
		weaponMagicModify.setFieldIModify(ModifyField.ATTACK_HIT);
		weaponMagicModify.setModifyField(ModifyField.WEAPON_MAGIC_BONUS);
		weaponGameEntity.addModify(weaponMagicModify);

		// Set weapon finesse
		Modify finesseModify = new Modify();
		finesseModify.setModifyField(ModifyField.FINESSE);
		finesseModify.setFieldIModify(ModifyField.ATTACK_HIT_MOD);
		finesseModify.addFieldThatModifyMe(ModifyField.DEXTERITY_MOD);
		finesseModify.addFieldThatModifyMe(ModifyField.STRENGTH_MOD);
		finesseModify.setModifyOperation(ModifyOperation.MAX);
		weaponGameEntity.addModify(finesseModify);

		// Create ATTACK_HIT_MOD Modify
		Modify attackHitModModify = new Modify();
		attackHitModModify.setModifyField(ModifyField.ATTACK_HIT_MOD);
		attackHitModModify.setFieldIModify(ModifyField.ATTACK_HIT);
		attackHitModModify.setModifyOperation(ModifyOperation.MAX);
		weaponGameEntity.addModify(attackHitModModify);

		// Actually equip weapon
		character.addItem(weapon);
		character.equip(weapon);

		// Check ATTACK_HIT_MOD

		// Check ATTACK_HIT
		int attackHit = character.getAttackHit();
		assertThat(attackHit, is(profBonus + weaponMagicBonus + Math.max(strengthModAmount, dexModAmount)));
	}

	@Test
	public void modifyByLevelTest() {
		// Create Character, level already set
		Character character = new Character();

		// Create class and stuff to not get null pointer exception when calling
		// setCurrentLevel
		Level level1 = new Level();
		level1.setProficiencyBonus(1);
		level1.setLevel(1);

		Level level4 = new Level();
		level4.setProficiencyBonus(4);
		level4.setLevel(4);

		LevelingTable levelingTable = new LevelingTable();
		levelingTable.setLevels(Arrays.asList(level1, level4));

		Classs classs = new Classs();
		classs.setLevelingTable(levelingTable);
		classs.setGameEntity(new GameEntity());

		character.setClasss(classs);

		// Create modify based on level
		Modify hillDwarfModify = new Modify();
		hillDwarfModify.setBase(0);
		hillDwarfModify.setModifyField(ModifyField.HP_MOD);
		hillDwarfModify.addFieldThatModifyMe(ModifyField.LEVEL);

		character.addModify(hillDwarfModify);

		int hp = character.getHP();
		assertThat(hp, is(1));

		// change character level and test hp again
		character.setCurrentLevel(4);
		hp = character.getHP();
		assertThat(hp, is(4));
	}

	@Test
	public void testRage() {
		// Create character
		Character character = new Character();

		// RAGE
		Modify rageModify = new Modify();
		rageModify.setModifyField(ModifyField.RAGE_DAMAGE);
		rageModify.setFieldsIModify(Arrays.asList(ModifyField.DAMAGE));

		String rage = "rage";
		GameEntity rageGameEntity = new GameEntity();
		rageGameEntity.setName(rage);
		rageGameEntity.setModify(rageModify);

		Feature rageFeature = new Feature();
		rageFeature.setGameEntity(rageGameEntity);
		String ragePool = "rages";
		rageFeature.setResourcePool(ragePool);

		// Those extra columns
		int rageAmount = 2;
		Modify ragePoolModify = new Modify();
		ragePoolModify.setBase(rageAmount);
		ragePoolModify.setModifyField(ModifyField.RAGE_POOL);
		GameEntity ragePoolGameEntity = new GameEntity();
		ragePoolGameEntity.setName(ragePool);
		ragePoolGameEntity.setModify(ragePoolModify);

		int rageDamage = 2;
		Modify rageDamageModify = new Modify();
		rageDamageModify.setBase(rageDamage);
		rageDamageModify.setModifyField(ModifyField.RAGE_DAMAGE_MOD);
		rageDamageModify.setFieldIModify(ModifyField.RAGE_DAMAGE);
		GameEntity rageDamageGameEntity = new GameEntity();
		rageDamageGameEntity.setModify(rageDamageModify);

		List<GameEntity> extraLevelColumn = new ArrayList<>();
		extraLevelColumn.add(ragePoolGameEntity);
		extraLevelColumn.add(rageDamageGameEntity);

		// Level & leveling table
		Level level1 = new Level();
		level1.setLevel(1);
		level1.setFeatures(Arrays.asList(rageFeature));
		level1.setProficiencyBonus(2);
		level1.setExtraLevelColumns(extraLevelColumn);

		LevelingTable levelingTable = new LevelingTable();
		levelingTable.setLevels(Arrays.asList(level1));

		// Create classs with leveling table that has level that has RAGE feature
		Classs classs = new Classs();
		classs.setLevelingTable(levelingTable);

		// Set Classs
		character.setClasss(classs);

		// See how many rages we got
		int ragesLeft = character.getFeatureUsesLeft(rageFeature);
		assertThat(ragesLeft, is(rageAmount));

		// Use a rage, get damage mod
		boolean useRage = character.useFeature(rageFeature);
		assertThat(useRage, is(true));
		int damage = character.getDamage();
		assertThat(damage, is(rageDamage));

		// finish rage, see how many rages we got
		boolean endRage = character.turnOffFeature(rageFeature);
		assertThat(endRage, is(true));
		ragesLeft = character.getFeatureUsesLeft(rageFeature);
		assertThat(ragesLeft, is(rageAmount - 1));
		damage = character.getDamage();
		assertThat(damage, is(0));

		useRage = character.useFeature(rageFeature);
		assertThat(useRage, is(true));
		ragesLeft = character.getFeatureUsesLeft(rageFeature);
		assertThat(ragesLeft, is(rageAmount - 2));
		damage = character.getDamage();
		assertThat(damage, is(rageDamage));

		endRage = character.turnOffFeature(rageFeature);
		assertThat(endRage, is(true));
		useRage = character.useFeature(rageFeature);
		assertThat(useRage, is(false));
		damage = character.getDamage();
		assertThat(damage, is(0));
	}

	@Test
	public void testUnarmoredAC() {
		Character character = new Character();

		int ac = character.getAC();
		assertThat(ac, is(10));

		// Set AbilityScore
		AbilityScore dex = new AbilityScore();
		int dexScore = 20; // 5, want larger so finesse makes us use this one
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Give the dex to the player
		character.addAbilityScore(dex, dexScore);

		ac = character.getAC();
		assertThat(ac, is(10 + dexModAmount));
	}

	@Test
	public void testUnarmoredDefenseFeature() {
		Character character = new Character();

		// Set AbilityScore - Constitution
		AbilityScore constitution = new AbilityScore();
		int constScore = 18; // 4
		int constModAmount = CharacterAbility.calculateModifier(constScore);

		// Constitution modifier
		Modify constMod = new Modify();
		constMod.setModifyField(ModifyField.CONSTITUTION_MOD);
		constMod.setBase(constModAmount);

		GameEntity constGameEntity = new GameEntity();
		constGameEntity.setName("Constitution");
		constGameEntity.setModify(constMod);
		constitution.setGameEntity(constGameEntity);

		// Set AbilityScore - Dexterity
		AbilityScore dex = new AbilityScore();
		int dexScore = 20; // 5
		int dexModAmount = CharacterAbility.calculateModifier(dexScore);

		// Dex modifier
		Modify dexMod = new Modify();
		dexMod.setModifyField(ModifyField.DEXTERITY_MOD);
		dexMod.setBase(dexModAmount);

		GameEntity dexGameEntity = new GameEntity();
		dexGameEntity.setName("Dexterity");
		dexGameEntity.setModify(dexMod);
		dex.setGameEntity(dexGameEntity);

		// Give the dex and constitution to the player
		character.addAbilityScore(dex, dexScore);
		character.addAbilityScore(constitution, constScore);

		// Unarmored defense mod
		Modify unarmoredDefMod = new Modify();
		unarmoredDefMod.setBase(10);
		unarmoredDefMod.setModifyField(ModifyField.UNARMORED_DEF);
		unarmoredDefMod.addFieldThatModifyMe(ModifyField.DEXTERITY_MOD);
		unarmoredDefMod.addFieldThatModifyMe(ModifyField.CONSTITUTION_MOD);
		unarmoredDefMod.setFieldIModify(ModifyField.CHARACTER_AC);

		// Wire into a game entity -> feature -> level -> leveling table -> class
		GameEntity unarmoredDefGameEntity = new GameEntity();
		unarmoredDefGameEntity.setModify(unarmoredDefMod);
		Feature unarmoredDefFeature = new Feature();
		unarmoredDefFeature.setGameEntity(unarmoredDefGameEntity);
		unarmoredDefFeature.setPassive(true);
		Level level1 = new Level();
		level1.setProficiencyBonus(2);
		level1.setLevel(1);
		level1.setFeatures(Arrays.asList(unarmoredDefFeature));
		LevelingTable levelingTable = new LevelingTable();
		levelingTable.setLevels(Arrays.asList(level1));
		Classs classs = new Classs();
		classs.setLevelingTable(levelingTable);

		// Give classs to character
		character.setClasss(classs);

		int ac = character.getAC();
		assertThat(ac, is(10 + dexModAmount + constModAmount));
	}

	@Test
	public void testEquipAndUnequipArmor() {
		// Make character
		Character character = new Character();

		// Make armor - Plate
		int plateArmorBase = 18;
		Modify plateArmorModify = new Modify();
		plateArmorModify.setBase(plateArmorBase);
		plateArmorModify.setModifyField(ModifyField.ARMOR_AC);
		plateArmorModify.setFieldIModify(ModifyField.CHARACTER_AC);
		GameEntity plateArmorGameEntity = new GameEntity();
		plateArmorGameEntity.setModify(plateArmorModify);
		plateArmorGameEntity.setName("Plate");
		Armor plateArmor = new Armor();
		plateArmor.setGameEntity(plateArmorGameEntity);

		// Make armor - Splint
		int splintArmorBase = 17;
		Modify splintArmorModify = new Modify();
		splintArmorModify.setBase(splintArmorBase);
		splintArmorModify.setModifyField(ModifyField.ARMOR_AC);
		splintArmorModify.setFieldIModify(ModifyField.CHARACTER_AC);
		GameEntity splintArmorGameEntity = new GameEntity();
		splintArmorGameEntity.setModify(splintArmorModify);
		splintArmorGameEntity.setName("Splint");
		Armor splintArmor = new Armor();
		splintArmor.setGameEntity(splintArmorGameEntity);

		// Add armor to inventory
		character.addItem(splintArmor);
		character.addItem(plateArmor);

		// Put on armor and test AC
		character.equip(plateArmor);
		int ac = character.getAC();
		assertThat(ac, is(plateArmorBase));

		// Take off armor and test AC
		character.unequip(plateArmor);
		ac = character.getAC();
		assertThat(ac, is(10));

		// Put on different armor and test AC
		character.equip(splintArmor);
		ac = character.getAC();
		assertThat(ac, is(splintArmorBase));
	}
}
