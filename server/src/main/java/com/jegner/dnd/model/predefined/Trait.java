package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Predefined
public class Trait {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;

	@Transient
	@Getter
	@Setter
	private static List<Trait> commonTraits;

	public static Trait findCommonTraitByName(String name) {
		return commonTraits.stream().filter(trait -> trait.getGameEntity().getName().equalsIgnoreCase(name)).findFirst()
				.get();
	}
}
