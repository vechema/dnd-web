package com.jegner.dnd.utility;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Duration {
	
	private int amount;
	private TimeUnit timeUnit;

}
