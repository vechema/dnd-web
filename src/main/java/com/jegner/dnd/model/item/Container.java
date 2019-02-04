package com.jegner.dnd.model.item;

import java.util.List;

import lombok.Data;

@Data
public class Container extends Item {
	private int capacityInLBs;

	private static List<Container> containers;
}
