package com.jegner.dnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jegner.dnd.database.PrefetchDataUtility;

@SpringBootApplication
public class DndBackendApplication implements ApplicationRunner {

	@Autowired
	PrefetchDataUtility prefetchData;

	public static void main(String[] args) {
		SpringApplication.run(DndBackendApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		prefetchData.initializeAllData();
	}

}
