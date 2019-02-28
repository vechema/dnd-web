package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.LanguageScript;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Entity
@Data
@Predefined
public class Language {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private GameEntity gameEntity;
	private boolean isStandard;
	@ElementCollection
	private List<Race> typicalSpeakers;
	private LanguageScript script;
}
