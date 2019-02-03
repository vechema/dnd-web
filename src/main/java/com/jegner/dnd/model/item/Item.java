package com.jegner.dnd.model.item;

import java.util.List;

import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Item {
	
	private GameEntity entity;	
	private double value;
	private double weight;

	private static List<Item> items;
}
