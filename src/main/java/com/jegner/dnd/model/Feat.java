package com.jegner.dnd.model;

import java.util.List;

import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Feat {
	private GameEntity entity;

	private static List<Feat> feats;
}
