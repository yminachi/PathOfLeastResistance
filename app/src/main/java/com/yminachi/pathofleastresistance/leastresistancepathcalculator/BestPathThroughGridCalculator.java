package com.yminachi.pathofleastresistance.leastresistancepathcalculator;

import org.apache.commons.math.linear.RealMatrix;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;

public class BestPathThroughGridCalculator {
    private BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator;
    private MinimumPathPicker minimumPathPicker;

    public BestPathThroughGridCalculator(BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator,
                                         MinimumPathPicker minimumPathPicker) {
        this.bestPathsThroughColumnCalculator = bestPathsThroughColumnCalculator;
        this.minimumPathPicker = minimumPathPicker;
    }

    public Path calculateBestPathThroughGrid(RealMatrix grid, double maxTotal) {
        Map<Integer, Path> previousColumnsPaths = buildInitialColumnPaths(grid.getColumn(1));

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

    private Map<Integer, Path> buildInitialColumnPaths(double[] firstColumn){
        Map<Integer,Path> initialColumnPaths = new HashMap<>();
        for(int index = 0;index<firstColumn.length;index++){
            initialColumnPaths.put(index, new Path(singletonList(index), firstColumn[index]));
        }
        return initialColumnPaths;
    }
}
