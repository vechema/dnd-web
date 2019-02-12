package com.jegner.dnd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Experience {

	@Id
	@GeneratedValue
	private Long id;

	private int experiencePoints;

	public int getLevel() {
		return getLevel(this.experiencePoints);
	}

	public static int getLevel(int experiencePoints2) {

		return 0;
	}
}
