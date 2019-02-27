package com.jegner.dnd.model.predefined;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class Feature {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
}
