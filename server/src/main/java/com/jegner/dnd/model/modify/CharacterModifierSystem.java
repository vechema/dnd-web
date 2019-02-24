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
public class CharacterModifierSystem {

	@Id
	@GeneratedValue
	private long id;

	@OneToMany
	List<Modify> modifies;

	public CharacterModifierSystem() {
		modifies = new ArrayList<>();
	}

	public void addModify(Modify nMod) {
		ModifyField nModField = nMod.getModifyField();
		// If eMod modifies nMod, add eMod to nMod's list of modifiers & add nMod to
		// eMod's list of modifieds
		modifies.stream().filter(eMod -> eMod.getFieldsIModify().contains(nModField))
				.forEach(eMod -> nMod.getFieldsThatModifyMe().add(eMod.getModifyField()));

		modifies.stream().filter(eMod -> nMod.getFieldsThatModifyMe().contains(eMod.getModifyField()))
				.forEach(eMod -> eMod.getFieldsIModify().add(nModField));

		// If nMod modifies emod, add nMod to eMod's list of modifiers & add eMod to
		// nMod's list of modifieds
		modifies.stream().filter(eMod -> eMod.getFieldsThatModifyMe().contains(nModField))
				.forEach(eMod -> nMod.getFieldsIModify().add(eMod.getModifyField()));

		modifies.stream().filter(eMod -> nMod.getFieldsIModify().contains(eMod.getModifyField()))
				.forEach(eMod -> eMod.getFieldsThatModifyMe().add(nModField));

		// Finally, add it the the list
		modifies.add(nMod);
	}

	public int getCharacterAC() {
		return getCalculated(ModifyField.CHARACTER_AC, 0);
	}

	public int getCharacterInitiative() {
		return getCalculated(ModifyField.INITIATIVE, 0);
	}

	/**
	 * Get Modify instances that modify the input field
	 * 
	 * @param modfield
	 * @return
	 */
	public List<Modify> getModifyOfModField(ModifyField modfield) {
		return modifies.stream().filter(mod -> mod.getFieldsIModify().contains(modfield)).collect(Collectors.toList());
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
		for (Modify modify : modifies) {
			// if that modify modifies the field, add to result
			if (modify.getFieldsIModify().contains(fieldBeingModified)) {
				result += modify.getBase();
				// If that modify has something that modifies IT, need to look for it
				for (Modify modifier : getModifyOfModField(modify.getModifyField())) {
					return getCalculated(modify.getModifyField(), result);
				}
			}
		}
		return result;
	}

}
