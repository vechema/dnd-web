package com.jegner.dnd.model.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class MountVehicle extends Container {

	@Id
	@GeneratedValue
	private Long id;

	private int speed;
	private Terrain terrain;
}
