package com.jegner.dnd.model.modify;

import java.util.ArrayList;
import java.util.List;
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

	public int getCharacterAC() {
		return getCalculated(ModifyField.CHARACTER_AC, 0);
	}

	public int getCharacterInitiative() {
		return getCalculated(ModifyField.INITIATIVE, 0);
	}

	public int getCharacterAttackHit() {
		return getCalculated(ModifyField.ATTACK_HIT, 0);
	}

	public int getCharacterHP() {
		return getCalculated(ModifyField.HP, 0);
	}

	public int getCharacterDamage() {
		return getCalculated(ModifyField.DAMAGE, 0);
	}

	/**
	 * Get Modify instances that modify the input field
	 * 
	 * @param modfield
	 * @return
	 */
	public List<Modify> getModifyOfModField(ModifyField modfield) {
		return modifys.stream().filter(mod -> mod.getFieldsIModify().contains(modfield)).collect(Collectors.toList());
	}

	/**
	 * for the field being modified, find all things that modify it and add their
	 * mod amount
	 * 
	 * @param fieldBeingModified
	 * @return
	 */
	private int getCalculated(ModifyField fieldBeingModified, int result) {
		// Look through all modifies
		for (Modify modify : modifys) {
			// if that modify modifies the field, add to result
			if (modify.getFieldsIModify().contains(fieldBeingModified)) {
				result += modify.getBase();
				// If that modify has something that modifies IT, need to look for it
				result += modify.getFieldThatModifiesMeAmount(getModifyOfModField(modify.getModifyField()));
			}
		}
		return result;
	}

}
