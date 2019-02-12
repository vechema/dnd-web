package com.jegner.dnd.model.party;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
@Entity
public class Location {
	@Id
	@GeneratedValue
	private long id;
	@OneToOne(cascade = CascadeType.ALL)
	private GameEntity gameEntity;
	private Climate climate;
	@ElementCollection
	private List<LocationType> locationTypes;
}
