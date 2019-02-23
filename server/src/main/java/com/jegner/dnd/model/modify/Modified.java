package com.jegner.dnd.model.modify;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
/**
 * An object that is modified by something else
 * 
 * @author Jo
 *
 */
public class Modified {

	@GeneratedValue
	@Id
	private Long id;
	private int base;
	/**
	 * Field that is modified (AC, HP, etc)
	 */
	private ModifiedField modifiedField;
	@ElementCollection
	/**
	 * What is effecting the modified field
	 */
	private List<Modifier> modifiers;

	// Need to put blank constructor for JPA because adding the string one got rid
	// of the default no param constructor
	public Modified() {

	}

	// Added because JPA complained:
	// Cannot construct instance of `com.jegner.dnd.model.modify.Modified` (although
	// at least one Creator exists): no String-argument constructor/factory method
	// to deserialize from String value ('14')
	public Modified(String noop) {

	}

	public int getModAmount() {
		int modAmount = base;
		for (Modifier modifier : modifiers) {
			modAmount += modifier.getModAmount(modifiedField);
		}
		return modAmount;
	}
}
