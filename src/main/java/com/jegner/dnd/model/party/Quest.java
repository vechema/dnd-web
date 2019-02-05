package com.jegner.dnd.model.party;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.item.Money;

import lombok.Data;

@Data
@Entity
public class Quest {
	@Id
	@GeneratedValue
	private long id;
	private String goal;
	@OneToOne
	private Money reward;
	private String notes;
	@OneToOne
	private NPC giver;
	private QuestStatus questStatus;
}
