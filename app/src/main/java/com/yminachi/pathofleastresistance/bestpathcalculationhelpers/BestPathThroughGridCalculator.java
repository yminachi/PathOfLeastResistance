package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.apache.commons.math.linear.RealMatrix;

import java.util.Map;

public class BestPathThroughGridCalculator {
	private BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator;
	private MinimumPathPicker minimumPathPicker;
	private InitialColumnPathsBuilder initialColumnPathsBuilder;

	public BestPathThroughGridCalculator() {
		bestPathsThroughColumnCalculator = new BestPathsThroughColumnCalculator();
		initialColumnPathsBuilder = new InitialColumnPathsBuilder();
		minimumPathPicker = new MinimumPathPicker();
	}

	public void setInitialColumnPathsBuilder(InitialColumnPathsBuilder initialColumnPathsBuilder) {
		this.initialColumnPathsBuilder = initialColumnPathsBuilder;
	}

	public void setMinimumPathPicker(MinimumPathPicker minimumPathPicker) {
		this.minimumPathPicker = minimumPathPicker;
	}

	void setBestPathsThroughColumnCalculator(BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator){
		this.bestPathsThroughColumnCalculator = bestPathsThroughColumnCalculator;
	}

	public Path calculateBestPathThroughGrid(RealMatrix grid, double maxTotal) {
		Map<Integer, Path> previousColumnsPaths = initialColumnPathsBuilder.buildInitialColumnPaths(grid.getColumnDimension());

		for (int column = 1; column <= grid.getColumnDimension(); column++) {
			Map<Integer, Path> thisColumnsPaths;

			thisColumnsPaths = bestPathsThroughColumnCalculator.getBestPathsForColumn(grid.getColumn(column), previousColumnsPaths, maxTotal);
			if (thisColumnsPaths.isEmpty()) {
				return minimumPathPicker.pickMinimumPath(previousColumnsPaths.values());
			}
			previousColumnsPaths = thisColumnsPaths;
		}

		return minimumPathPicker.pickMinimumPath(previousColumnsPaths.values());
	}
}
