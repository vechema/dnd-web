package com.jegner.dnd.model.party;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.Alignment;
import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
@Entity
public class NPC {
	@Id
	@GeneratedValue
	private long id;
	@OneToOne
	private GameEntity entity;
	private Alignment alignment;
	@OneToOne
	private Location location;
}
