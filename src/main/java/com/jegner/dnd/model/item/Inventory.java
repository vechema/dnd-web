package com.jegner.dnd.model.item;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Inventory {

	@Id
	@GeneratedValue
	private Long id;
	@ElementCollection
	private Map<Item, Integer> items;
}
