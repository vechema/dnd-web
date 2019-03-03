package com.jegner.dnd.model.magic;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.Attack;
import com.jegner.dnd.utility.Duration;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Entity
@Data
@Predefined
public class SpellLevel {

	@GeneratedValue
	@Id
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Duration duration;
	private String areaOfEffect;
	@OneToOne(cascade = CascadeType.ALL)
	private Attack attack;
	private String description;
}
