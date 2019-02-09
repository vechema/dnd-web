package com.jegner.dnd.database.predefined;

import java.net.MalformedURLException;
import java.util.List;

public interface PredefinedMaker<T> {
	public List<T> generatePredefineds() throws MalformedURLException;
}
