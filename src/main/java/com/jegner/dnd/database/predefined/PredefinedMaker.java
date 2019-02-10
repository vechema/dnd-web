package com.jegner.dnd.database.predefined;

import java.util.List;

public interface PredefinedMaker<T> {
	public List<T> generatePredefineds() throws Exception;
}
