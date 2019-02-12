package com.jegner.dnd.model.predefined;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jegner.dnd.model.LanguageScript;
import com.jegner.dnd.utility.PreDefined;

import lombok.Data;

@Entity
@Data
@PreDefined
public class Language {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private boolean isStandard;
	@ElementCollection
	private List<Race> typicalSpeakers;
	private LanguageScript script;
}
