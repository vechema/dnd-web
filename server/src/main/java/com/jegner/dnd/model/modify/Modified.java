package com.jegner.dnd.model.modify;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Modified {

	@GeneratedValue
	@Id
	private Long id;
	private int base;
	@ElementCollection
	private List<ModifierField> modifierClasses;
	private ModifiedField modifiedField;

	// Added because JPA complained:
	// Cannot construct instance of `com.jegner.dnd.model.modify.Modified` (although
	// at least one Creator exists): no String-argument constructor/factory method
	// to deserialize from String value ('14')
	public Modified(String noop) {

	}
}
