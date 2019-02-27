package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Background {

	@Id
	@GeneratedValue
	private long id;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	@OneToOne
	private Proficiency startingProficiencies;
	@OneToMany
	private List<Language> languages;
	@OneToMany
	private List<Item> startingItems;
}
