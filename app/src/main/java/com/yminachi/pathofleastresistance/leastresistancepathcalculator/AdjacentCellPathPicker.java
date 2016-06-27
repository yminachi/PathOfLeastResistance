package com.yminachi.pathofleastresistance.leastresistancepathcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AdjacentCellPathPicker {
    public List<Path> pickAdjacentPaths(Map<Integer, Path> lastColumnsPaths, int currentRow) {
        int lastIndex = lastColumnsPaths.size() - 1;

        Path upwardDiagonalPath = currentRow == lastIndex ? lastColumnsPaths.get(0) : lastColumnsPaths.get(currentRow + 1);
        Path downwardDiagonalPath = currentRow == 0 ? lastColumnsPaths.get(lastIndex) : lastColumnsPaths.get(currentRow - 1);

        return Arrays.asList(lastColumnsPaths.get(currentRow), downwardDiagonalPath, upwardDiagonalPath);
    }
}
