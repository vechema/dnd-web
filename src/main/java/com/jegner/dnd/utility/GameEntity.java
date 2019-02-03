package com.jegner.dnd.utility;

import java.net.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class GameEntity {
	
	@GeneratedValue
	@Id
	private Long id;
	private String name;
	private String description;
	private URL referenceURL;

}
