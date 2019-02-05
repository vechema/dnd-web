package com.jegner.dnd.model.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Data
@Entity
@PreDefined
public class MountVehicle extends Container {

	@Id
	@GeneratedValue
	private Long id;

	private int speed;
	private Terrain terrain;
}
