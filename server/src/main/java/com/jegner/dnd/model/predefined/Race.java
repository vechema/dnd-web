package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.Size;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Race {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany
	private List<Trait> traits;
	private Size size;
	private int startingSpeed;
	@OneToMany
	private List<Language> languages;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
}
