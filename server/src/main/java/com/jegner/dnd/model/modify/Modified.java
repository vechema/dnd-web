package com.jegner.dnd.model.modify;

import java.util.List;

public interface Modified {
	public int getBase();

	public List<Class<Modifier>> getModClasses();

	public int getModCalc();
}
