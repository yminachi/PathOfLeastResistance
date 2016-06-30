package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.apache.commons.math.linear.RealMatrix;

import java.util.Map;

public class BestPathThroughGridCalculator {
    private BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator;
    private InitialColumnPathsBuilder initialColumnPathsBuilder;
    private MinimumPathPicker minimumPathPicker;

    public BestPathThroughGridCalculator(BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator,
                                         InitialColumnPathsBuilder initialColumnPathsBuilder,
                                         MinimumPathPicker minimumPathPicker) {
        this.bestPathsThroughColumnCalculator = bestPathsThroughColumnCalculator;
        this.initialColumnPathsBuilder = initialColumnPathsBuilder;
        this.minimumPathPicker = minimumPathPicker;
    }

    public Path calculateBestPathThroughGrid(RealMatrix grid, double maxTotal) {
        Map<Integer, Path> previousColumnsPaths = initialColumnPathsBuilder.buildInitialColumnPaths(grid.getColumn(1));

        for (int column = 2; column <= grid.getColumnDimension(); column++) {
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
