package com.jegner.dnd.model.party;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jegner.dnd.model.Character;
import com.jegner.dnd.model.item.Money;

import lombok.Data;

@Data
@Entity
public class Party {
	@Id
	@GeneratedValue
	private long id;
	@OneToMany
	private List<Character> characters;
	@OneToOne
	private Money partyGold;
}
