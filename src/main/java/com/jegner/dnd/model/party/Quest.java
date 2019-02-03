package com.jegner.dnd.model.party;

import com.jegner.dnd.model.item.Money;

import lombok.Data;

@Data
public class Quest {
	private String goal;
	private Money reward;
	private String notes;
	private NPC giver;
	private QuestStatus questStatus;
}
