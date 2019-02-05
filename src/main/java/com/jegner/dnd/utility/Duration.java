package com.jegner.dnd.utility;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Duration {

	@GeneratedValue
	@Id
	private Long id;

	private int amount;
	private TimeUnit timeUnit;

}
