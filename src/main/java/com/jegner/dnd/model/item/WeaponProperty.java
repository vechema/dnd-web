package com.jegner.dnd.model.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Entity
@Data
@Prefetched
public class WeaponProperty {

	@GeneratedValue
	@Id
	private Long id;
	@OneToOne
	private GameEntity entity;

}
