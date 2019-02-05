package com.jegner.dnd.model.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jegner.dnd.utility.Prefetched;

import lombok.Data;

@Data
@Entity
@Prefetched
public class Container extends Item {

	@Id
	@GeneratedValue
	private Long id;
	private int capacityInLBs;

}
