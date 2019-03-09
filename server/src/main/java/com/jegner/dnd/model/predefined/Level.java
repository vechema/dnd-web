package com.jegner.dnd.model.predefined;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jegner.dnd.model.modify.Modify;
import com.jegner.dnd.model.modify.ModifyField;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Entity
@Predefined
public class Level {

	@Id
	@GeneratedValue
	private Long id;

	private int level;
	@OneToOne(cascade = CascadeType.ALL)
	private Modify proficiencyBonus;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Feature> features;
	@ElementCollection
	private Map<Integer, Integer> spellLevelToSlots;
	@OneToMany(cascade = CascadeType.ALL)
	// Used for things not common across all classes like rage, ki, sneak attack
	private List<GameEntity> extraLevelColumns;

	public Level() {
		features = new ArrayList<>();
		extraLevelColumns = new ArrayList<>();
	}

	@Tolerate
	@JsonSetter("proficiencyBonus")
	public void setProficiencyBonus(int bonus) {
		Modify proficiencyBonusModify = new Modify();
		proficiencyBonusModify.setBase(bonus);
		proficiencyBonusModify.setModifyField(ModifyField.PROFICIENCY);

		this.proficiencyBonus = proficiencyBonusModify;
	}
}
