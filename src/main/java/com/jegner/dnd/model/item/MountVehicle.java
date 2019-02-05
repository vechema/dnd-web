package com.jegner.dnd.model.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
public class MountVehicle extends Container {

	@Id
	@GeneratedValue
	private Long id;

	private int speed;
	private Terrain terrain;
}
