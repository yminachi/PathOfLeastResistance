package com.yminachi.pathofleastresistance.leastresistancepathcalculator;

import java.util.ArrayList;
import java.util.List;

public class BestPathThroughCellCalculator {

    private MinimumPathPicker minimumPathPicker;

    public BestPathThroughCellCalculator(MinimumPathPicker minimumPathPicker){
        this.minimumPathPicker = minimumPathPicker;
    }

    public Path getBestPathThroughCell(List<Path> previousAdjacentPaths, int rowIndex, double cellValue){
        Path previousPath = minimumPathPicker.pickMinimumPath(previousAdjacentPaths);

        List<Integer> newRowsInPath = new ArrayList<>(previousPath.getRowsInPath());
        newRowsInPath.add(rowIndex);

        return new Path(newRowsInPath, cellValue + previousPath.getPathTotal());
    }
}
