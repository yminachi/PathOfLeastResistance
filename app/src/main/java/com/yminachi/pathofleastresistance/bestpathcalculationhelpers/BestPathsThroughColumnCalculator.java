package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestPathsThroughColumnCalculator {
	private AdjacentCellPathPicker adjacentCellPathPicker;
	private BestPathThroughCellCalculator bestPathThroughCellCalculator;

	public BestPathsThroughColumnCalculator() {
		adjacentCellPathPicker = new AdjacentCellPathPicker();
		bestPathThroughCellCalculator = new BestPathThroughCellCalculator();
	}

	void setAdjacentCellPathPicker(AdjacentCellPathPicker adjacentCellPathPicker){
		this.adjacentCellPathPicker = adjacentCellPathPicker;
	}

	void setBestPathThroughCellCalculator(BestPathThroughCellCalculator bestPathThroughCellCalculator){
		this.bestPathThroughCellCalculator = bestPathThroughCellCalculator;
	}

	public Map<Integer, Path> getBestPathsForColumn(double[] column, Map<Integer, Path> previousBestPathsForColumn, double maxTotal) {
		Map<Integer, Path> bestPathsForColumn = new HashMap<>();

		for (int rowIndex = 0; rowIndex < column.length; rowIndex++) {
			Path pathThroughCell = getBestPathThroughCell(previousBestPathsForColumn, rowIndex, column[rowIndex]);

			if (pathThroughCell != null && pathThroughCell.getPathTotal() <= maxTotal) {
				bestPathsForColumn.put(rowIndex, pathThroughCell);
			}
		}

		return bestPathsForColumn;
	}

	private Path getBestPathThroughCell(Map<Integer, Path> previousBestPathsForColumn, int rowIndex, double cellValue) {
		List<Path> adjacent = adjacentCellPathPicker.pickAdjacentPaths(previousBestPathsForColumn, rowIndex);

		return adjacent.isEmpty() ? null : bestPathThroughCellCalculator.getBestPathThroughCell(adjacent, rowIndex, cellValue);
	}
}
