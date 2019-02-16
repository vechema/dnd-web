package com.jegner.dnd.model.modify;

import java.util.List;

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
	List<Modifier> modifiers;
	@OneToMany
	List<Modified> modifieds;

	public int getCharacterAC() {
		return getCalculated(ModifiedField.AC);
	}

	private int getCalculated(ModifiedField modifiedField) {
		int result = 0;
		for (Modifier modifier : modifiers) {
			if (modifier.getModifierFields().containsKey(modifiedField)) {
				result += modifier.getModifierFields().get(modifiedField);
			}
		}
		return result;
	}

}
