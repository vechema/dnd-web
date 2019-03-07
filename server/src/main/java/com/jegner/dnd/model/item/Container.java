package com.jegner.dnd.model.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.jegner.dnd.utility.Predefined;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Predefined
public class Container extends Item {

	@Id
	@GeneratedValue
	private Long id;
	private String capacity;

	@Transient
	@Getter
	@Setter
	private static List<Container> containers = new ArrayList<>();

	public static Container findContainerByName(String name) {
		return containers.stream().filter(container -> container.getGameEntity().getName().equalsIgnoreCase(name))
				.findFirst().get();
	}

}
