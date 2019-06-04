package com.jegner.dnd.model.predefined;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Feature {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private String resourcePool;
	private boolean isPassive;

	/**
	 * Override because adding it's gameEntity -> modifys to the character mod
	 * system can change the hashcode
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 1;
	}
}
