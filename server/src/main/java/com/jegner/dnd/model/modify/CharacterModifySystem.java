package com.jegner.dnd.model.modify;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * This class used for all of the crazy calculations that a character must do to
 * figure out verious stats like HP, hit, damage, ac, etc
 * 
 * It works by the Character class adding and removing Modify instances as the
 * character uses or stops using certain items, features, spells, etc
 * 
 * @author Jo
 *
 */
@Data
@Entity
public class CharacterModifySystem {

	@Id
	@GeneratedValue
	private long id;

	@OneToMany
	List<Modify> modifys;

	public CharacterModifySystem() {
		modifys = new ArrayList<>();
	}

	public void addModify(Modify nMod) {
		if (modifys.contains(nMod)) {
			return;
		}
		ModifyField nModField = nMod.getModifyField();
		// If eMod modifies nMod, add eMod to nMod's list of modifiers & add nMod to
		// eMod's list of modifieds
		modifys.stream()
				.filter(eMod -> eMod.getFieldsIModify().contains(nModField)
						&& !nMod.getFieldsThatModifyMe().containsKey(eMod.getModifyField()))
				.forEach(eMod -> nMod.getFieldsThatModifyMe().put(eMod.getModifyField(), Integer.MAX_VALUE));

		modifys.stream()
				.filter(eMod -> nMod.getFieldsThatModifyMe().containsKey(eMod.getModifyField())
						&& !eMod.getFieldsIModify().contains(nModField))
				.forEach(eMod -> eMod.getFieldsIModify().add(nModField));

		// If nMod modifies emod, add nMod to eMod's list of modifiers & add eMod to
		// nMod's list of modifieds
		modifys.stream()
				.filter(eMod -> eMod.getFieldsThatModifyMe().containsKey(nModField)
						&& !nMod.getFieldsIModify().contains(eMod.getModifyField()))
				.forEach(eMod -> nMod.getFieldsIModify().add(eMod.getModifyField()));

		modifys.stream()
				.filter(eMod -> nMod.getFieldsIModify().contains(eMod.getModifyField())
						&& !eMod.getFieldsThatModifyMe().containsKey(nModField))
				.forEach(eMod -> eMod.getFieldsThatModifyMe().put(nModField, Integer.MAX_VALUE));

		// Finally, add it the the list
		modifys.add(nMod);
	}

	public void addModifys(List<Modify> modifys) {
		modifys.stream().forEach(modify -> addModify(modify));
	}

	public void removeModify(Modify modify) {
		modifys.remove(modify);
	}

	public void removeModifys(List<Modify> modifys) {
		modifys.stream().forEach(modify -> removeModify(modify));
	}

	public int getCharacterAC() {
		return getCalculated(ModifyField.CHARACTER_AC);
	}

	public int getCharacterInitiative() {
		return getCalculated(ModifyField.INITIATIVE);
	}

	public int getCharacterAttackHit() {
		return getCalculated(ModifyField.ATTACK_HIT);
	}

	public int getCharacterHP() {
		return getCalculated(ModifyField.HP);
	}

	public int getCharacterDamage() {
		return getCalculated(ModifyField.DAMAGE);
	}

	/**
	 * Gets modifys whose modify field is the input
	 * 
	 * @param modfield
	 * @return
	 */
	private List<Modify> getModifysForModField(ModifyField modfield) {
		return modifys.stream().filter(mod -> mod.getModifyField().equals(modfield)).collect(Collectors.toList());
	}

	private int getCalculated(ModifyField fieldBeingCalculated) {
		return getModifysForModField(fieldBeingCalculated).stream()
				.mapToInt(modify -> modify.getFieldThatModifiesMeAmount(this)).sum();
	}
}
