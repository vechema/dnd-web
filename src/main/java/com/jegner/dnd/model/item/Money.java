package com.jegner.dnd.model.item;

import lombok.Data;

@Data
public class Money {
	private double gold;
	
	public double addCopper(double copper) {
		this.gold += copper/100;
		return this.gold;
	}
	
	public double addSilver(double silver) {
		this.gold += silver/10;
		return this.gold;
	}
	
	public double addGold(double gold) {
		this.gold += gold;
		return this.gold;
	}
	
	public double addPlatinum(double platinum) {
		this.gold += platinum*10;
		return this.gold;
	}
}
