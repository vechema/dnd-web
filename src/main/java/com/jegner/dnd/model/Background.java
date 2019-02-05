package com.jegner.dnd.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
public class Background {

	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private Proficiency startingProficiencies;
	@OneToMany
	private List<Language> languages;
	@OneToMany
	private List<Item> startingItems;
}
