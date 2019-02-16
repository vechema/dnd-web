package com.jegner.dnd.model.modify;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Modifier {

	@GeneratedValue
	@Id
	private Long id;
	private ModifierField modifierField;
	@ElementCollection
	private Map<ModifiedField, Integer> modifierFields;

	public int getModAmount(ModifierField modifierField) {
		return modifierFields.get(modifierField);
	}
}
