package com.jegner.dnd.model.item;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class EquipmentPack extends Item {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<InventoryItem> packItems;
}
