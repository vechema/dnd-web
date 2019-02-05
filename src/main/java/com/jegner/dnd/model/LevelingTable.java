package com.jegner.dnd.model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
public class LevelingTable {

	@Id
	@GeneratedValue
	private Long id;
	@OneToMany
	private Map<Integer, Level> levels;
}
