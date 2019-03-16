package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

/**
 * Represent each class's specialty like Barbarian Primal Path and Cleric Divine
 * Domain
 * 
 * @author Jo
 *
 */
@Data
@Entity
@Predefined
public class ClassSpecialty {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private int levelGranted;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClassSpecialtyLevel> classSpecialtyLevel;
}
