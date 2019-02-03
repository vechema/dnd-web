package com.jegner.dnd.model.party;

import java.util.List;

import com.jegner.dnd.model.item.Money;

import lombok.Data;

@Data
public class Party {
	private List<Character> characters;
	private Money partyGold;
}
