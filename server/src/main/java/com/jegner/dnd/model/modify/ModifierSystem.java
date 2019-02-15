package com.jegner.dnd.model.modify;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class ModifierSystem {

	@Id
	@GeneratedValue
	private long id;

	@Transient
	private Map<String, Modifier> classToInstanceMap;

	private Class getClazz(String clazz) throws ClassNotFoundException {
		return clazz != null ? Class.forName(clazz) : null;
	}

	private void setClazz(Class clazz) {
		clazz.getName();
	}

}
