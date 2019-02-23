package com.jegner.dnd.model.modify;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
/**
 * Something that modifies other values (AC, HP)
 * 
 * @author Jo
 *
 */
public class Modifier {

	@GeneratedValue
	@Id
	private Long id;
	/**
	 * Which field it is that modifies things
	 */
	private ModifierField modifierField;
	/**
	 * Map fields modded (HP, AC) -> how much modded
	 */
	@ElementCollection
	private Map<ModifiedField, Integer> modifierFields;

	public int getModAmount(ModifiedField modifiedField) {
		return modifierFields.get(modifiedField);
	}
}
