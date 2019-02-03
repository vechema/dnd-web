package com.jegner.dnd.model;

import lombok.Data;

@Data
public class Experience {
	
	private int experiencePoints;
	
	public int getLevel() {
		return getLevel(this.experiencePoints);
	}

	public static int getLevel(int experiencePoints2) {
		
		return 0;
	}
}
