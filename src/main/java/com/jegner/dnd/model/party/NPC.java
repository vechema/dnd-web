package com.jegner.dnd.model.party;

import com.jegner.dnd.model.Alignment;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class NPC {
	private GameEntity entity;
	private Alignment alignment;
	private Location location;
}
