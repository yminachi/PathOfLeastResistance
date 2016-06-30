package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;

public class InitialColumnPathsBuilder {
	public Map<Integer, Path> buildInitialColumnPaths(double[] firstColumn) {
		Map<Integer, Path> initialColumnPaths = new HashMap<>();
		for (int index = 0; index < firstColumn.length; index++) {
			initialColumnPaths.put(index, new Path(singletonList(index), firstColumn[index]));
		}
		return initialColumnPaths;
	}
}
