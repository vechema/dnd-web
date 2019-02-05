package com.jegner.dnd.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jegner.dnd.database.repo.GameEntityRepository;
import com.jegner.dnd.utility.GameEntity;

@Component
public class PrefetchDataUtility {

	@Autowired
	GameEntityRepository gameEntityRepo;

	public void initializeAllData() {
		gameEntityRepo.save(new GameEntity());
	}
}
