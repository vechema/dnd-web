package com.jegner.dnd.model.item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Entity
@Data
@Predefined
public class WeaponProperty {

	@GeneratedValue
	@Id
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;

}
