package com.jegner.dnd.database;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jegner.dnd.database.predefined.BackgroundPredefined;
import com.jegner.dnd.database.repo.BackgroundRepository;
import com.jegner.dnd.database.repo.GameEntityRepository;
import com.jegner.dnd.model.predefined.Background;
import com.jegner.dnd.utility.GameEntity;

@Component
public class PrefetchDataUtility {

	@Autowired
	GameEntityRepository gameEntityRepo;

	@Autowired
	BackgroundRepository backgroundRepo;

	public void initializeAllData() throws MalformedURLException {
		gameEntityRepo.save(new GameEntity());
		backgroundRepo.save(new Background());
		backgroundRepo.saveAll(BackgroundPredefined.predefinedBackgrounds());
	}
}
