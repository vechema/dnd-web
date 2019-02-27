package com.jegner.dnd.model.predefined;

import java.util.List;

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
	private List<Level> levels;

	public Level getLevel(int level) {
		return levels.stream().filter(levelObj -> levelObj.getLevel() == level).findFirst().get();
	}
}
