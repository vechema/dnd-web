package com.jegner.dnd.model.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Predefined
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private double valueInGold;
	private double weightInLbs;

	@Transient
	@Getter
	@Setter
	private static List<Item> items;

	public Item() {
		items = new ArrayList<>();
	}

	public static Item findItemByName(String name) {
		return items.stream().filter(item -> item.getGameEntity().getName().equalsIgnoreCase(name)).findFirst().get();
	}

	public static void addItems(List<? extends Item> newItems) {
		items.addAll(newItems);
	}
}
