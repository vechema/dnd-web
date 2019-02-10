package com.jegner.dnd.database.predefined;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jegner.dnd.database.repo.SkillRepository;
import com.jegner.dnd.model.predefined.Skill;

@Component
public class SkillPredefined implements PredefinedMaker<Skill> {

	@Autowired
	SkillRepository skillRepo;

	@Override
	public List<Skill> generatePredefineds() throws Exception {

		List<Skill> skills = new ArrayList<>();

		ObjectMapper mapper = new ObjectMapper();
		File skillFile = new File("src\\main\\resources\\database\\skill.json");
		Skill[] arcana = mapper.readValue(skillFile, Skill[].class);
		skills.addAll(Arrays.asList(arcana));

		skillRepo.saveAll(skills);
		return skills;
	}

}
