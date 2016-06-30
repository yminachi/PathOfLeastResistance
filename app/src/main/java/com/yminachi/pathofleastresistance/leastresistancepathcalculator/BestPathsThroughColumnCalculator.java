package com.yminachi.pathofleastresistance.leastresistancepathcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestPathsThroughColumnCalculator {
    private MinimumPathPicker minimumPathPicker;

    public BestPathsThroughColumnCalculator(MinimumPathPicker minimumPathPicker){
        this.minimumPathPicker = minimumPathPicker;
    }

    public Map<Integer, Path> getBestPathsForColumn(double[] column, Map<Integer, Path> previousBestPathsForColumn, double maxTotal){
        Map<Integer, Path> bestPathsForColumn = new HashMap<>();

        for (int rowIndex = 0; rowIndex < column.length; rowIndex++){
            Path pathThroughCell = getBestPathThroughCell(previousBestPathsForColumn, rowIndex, column[rowIndex]);

            if (pathThroughCell != null && pathThroughCell.getPathTotal() <= maxTotal){
                bestPathsForColumn.put(rowIndex, pathThroughCell);
            }
        }

        return bestPathsForColumn;
    }

    private List<Path> pickAdjacentPaths(Map<Integer, Path> lastColumnsPaths, int currentRow) {
        int lastIndex = lastColumnsPaths.size() - 1;

        Path upwardDiagonalPath = currentRow == lastIndex ? lastColumnsPaths.get(0) : lastColumnsPaths.get(currentRow + 1);
        Path downwardDiagonalPath = currentRow == 0 ? lastColumnsPaths.get(lastIndex) : lastColumnsPaths.get(currentRow - 1);

        return Arrays.asList(lastColumnsPaths.get(currentRow), downwardDiagonalPath, upwardDiagonalPath);
    }

    private Path getBestPathThroughCell(Map<Integer, Path> previousBestPathsForColumn, int rowIndex, double cellValue){
        List<Path> adjacent = pickAdjacentPaths(previousBestPathsForColumn, rowIndex);

        return adjacent.isEmpty() ? null : getBestPathThroughCell(adjacent, rowIndex, cellValue);
    }

    private Path getBestPathThroughCell(List<Path> previousAdjacentPaths, int rowIndex, double cellValue){
        Path previousPath = minimumPathPicker.pickMinimumPath(previousAdjacentPaths);

        List<Integer> newRowsInPath = new ArrayList<>(previousPath.getRowsInPath());
        newRowsInPath.add(rowIndex);

        return new Path(newRowsInPath, cellValue + previousPath.getPathTotal());
    }
}
