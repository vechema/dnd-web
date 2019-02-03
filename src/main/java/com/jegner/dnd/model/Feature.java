package com.jegner.dnd.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jegner.dnd.utility.GameEntity;

import lombok.Data;

@Data
public class Feature {
	
	private GameEntity entity;

	private static List<Feature> features;
}
