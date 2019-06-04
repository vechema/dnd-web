package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.jegner.dnd.model.LanguageScript;
import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Predefined
public class Language {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private boolean isStandard;
	private LanguageScript script;

	@Transient
	@Getter
	@Setter
	private static List<Language> languages;

	public static Language findLanguageByName(String name) {
		return languages.stream().filter(language -> language.getGameEntity().getName().equalsIgnoreCase(name))
				.findFirst().get();
	}
}
