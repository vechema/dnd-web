package com.jegner.dnd.model.modify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

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

	public int getArmorAC() {
		return getCalculated(ModifyField.ARMOR_AC);
	}

	public int getUnarmoredAC() {
		return getCalculated(ModifyField.UNARMORED_AC);
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
	 * Get Modify instances that modify the input field
	 * 
	 * @param modfield
	 * @return
	 */
	public List<Modify> getModifysOfModField(ModifyField modfield) {
		return modifys.stream().filter(mod -> mod.getFieldsIModify().contains(modfield)).collect(Collectors.toList());
	}

	/**
	 * Gets modifys whose modify field is the input
	 * 
	 * @param unarmoredAc
	 * @return
	 */
	private Collection<Modify> getModifysForModField(ModifyField modfield) {
		return modifys.stream().filter(mod -> mod.getModifyField().equals(modfield)).collect(Collectors.toList());
	}

	private int getBase(ModifyField modifyField) {
		return getModifysForModField(modifyField).stream().mapToInt(modify -> modify.getBase()).sum();
	}

	private int getCalculated(ModifyField fieldBeingCalculated) {
		return getCalculated(fieldBeingCalculated, getBase(fieldBeingCalculated));
	}

	/**
	 * for the field being modified, find all things that modify it and add their
	 * mod amount
	 * 
	 * @param fieldBeingCalculated
	 * @return
	 */
	private int getCalculated(ModifyField fieldBeingCalculated, int result) {
		// get all modifys that modify the field being calculated, ex: HP
		for (Modify modify : getModifysForModField(fieldBeingCalculated)) {
			// figure out the amount that the modify is modified by, ex: Amount HP_mod adds
			result += modify.getFieldThatModifiesMeAmount(getModifysOfModField(modify.getModifyField()));
			for (ModifyField modField : modify.getFieldsThatModifyMe().keySet()) {
				//for (Modify modifyMe : getModifysForModField(modField)) {
					//result += getCalculated(modifyMe.getModifyField(), result);
				//}
				result += getCalculated(modField,0);
			}
		}
		return result;
	}
	
	

}
