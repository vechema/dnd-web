package com.jegner.dnd.model.predefined;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jegner.dnd.utility.Predefined;

import lombok.Data;

@Data
@Entity
@Predefined
public class LevelingTable {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany
	private Map<Integer, Level> levels;
}
