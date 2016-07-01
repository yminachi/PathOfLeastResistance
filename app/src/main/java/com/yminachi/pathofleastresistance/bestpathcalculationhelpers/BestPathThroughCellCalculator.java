package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import java.util.ArrayList;
import java.util.List;

public class BestPathThroughCellCalculator {

	private MinimumPathPicker minimumPathPicker;

	public BestPathThroughCellCalculator() {
		minimumPathPicker = new MinimumPathPicker();
	}

	void setMinimumPathPicker(MinimumPathPicker minimumPathPicker){
		this.minimumPathPicker = minimumPathPicker;
	}

	public Path getBestPathThroughCell(List<Path> previousAdjacentPaths, int rowIndex, double cellValue) {
		Path previousPath = minimumPathPicker.pickMinimumPath(previousAdjacentPaths);

		List<Integer> newRowsInPath = new ArrayList<>(previousPath.getRowsInPath());
		newRowsInPath.add(rowIndex);

		return new Path(newRowsInPath, cellValue + previousPath.getPathTotal());
	}
}
