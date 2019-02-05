package com.jegner.dnd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;
import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
public class Feat {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private GameEntity entity;
}
