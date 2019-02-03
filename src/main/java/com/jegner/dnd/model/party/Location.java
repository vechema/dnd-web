package com.jegner.dnd.model.party;

import java.util.List;

import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Location {
	private GameEntity entity;
	private Climate climate;
	private List<LocationType> locationTypes;
}
