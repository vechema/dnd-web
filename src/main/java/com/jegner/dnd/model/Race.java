package com.jegner.dnd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
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
	@OneToOne
	private GameEntity entity;
}
