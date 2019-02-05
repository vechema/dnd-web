package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.Proficiency;
import com.jegner.dnd.model.item.Item;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Data
@Entity
@PreDefined
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
