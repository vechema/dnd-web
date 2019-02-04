package com.jegner.dnd.model;

import java.util.Map;

import lombok.Data;

@Data
public class LevelingTable {
	private Map<Integer, Level> levels;
}
