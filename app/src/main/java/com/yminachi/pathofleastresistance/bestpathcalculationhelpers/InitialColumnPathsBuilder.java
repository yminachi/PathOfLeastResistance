package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;

public class InitialColumnPathsBuilder {
	public Map<Integer, Path> buildInitialColumnPaths(int numberOfRows) {
		Map<Integer, Path> initialColumnPaths = new HashMap<>();
		for (int index = 0; index < numberOfRows; index++) {
			initialColumnPaths.put(index, new Path(Collections.<Integer>emptyList(), 0));
		}
		return initialColumnPaths;
	}
}
