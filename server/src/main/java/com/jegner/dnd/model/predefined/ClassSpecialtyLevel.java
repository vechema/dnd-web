package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.choice.Choice;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

/**
 * Represent each level of a Class Specialty
 * 
 * @author Jo
 *
 */
@Data
@Entity
@Predefined
public class ClassSpecialtyLevel {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private int levelGranted;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Choice> choices;
}
