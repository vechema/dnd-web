package com.jegner.dnd.model;

import java.util.List;

import lombok.Data;

@Data
public class Language {

	private String name;
	private boolean isStandard;
	private List<Race> typicalSpeakers;
	private LanguageScript script;

	private static List<Language> languages;
}
