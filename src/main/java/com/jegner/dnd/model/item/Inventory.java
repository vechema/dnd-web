package com.jegner.dnd.model.item;

import java.util.Map;

import lombok.Data;

@Data
public class Inventory {
	private Map<Item, Integer> items;
}
