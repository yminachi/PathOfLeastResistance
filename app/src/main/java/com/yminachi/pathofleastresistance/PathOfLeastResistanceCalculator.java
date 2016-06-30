package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.BestPathThroughGridCalculator;
import com.yminachi.pathofleastresistance.ioformatters.GridConverter;
import com.yminachi.pathofleastresistance.ioformatters.OutputFormatter;

import org.apache.commons.math.linear.RealMatrix;

public class PathOfLeastResistanceCalculator {
    private static final int MAX_TOTAL = 50;

    private BestPathThroughGridCalculator bestPathThroughGridCalculator;
    private GridConverter gridConverter;
    private OutputFormatter outputFormatter;

    public PathOfLeastResistanceCalculator(BestPathThroughGridCalculator bestPathThroughGridCalculator, GridConverter gridConverter, OutputFormatter outputFormatter){
        this.bestPathThroughGridCalculator = bestPathThroughGridCalculator;
        this.gridConverter = gridConverter;
        this.outputFormatter = outputFormatter;
    }

    public String calculatePathOfLeastResistance(String input){
        RealMatrix grid = gridConverter.convertToGrid(input);
        int numberOfColumns = grid.getColumnDimension();
        Path bestPath = bestPathThroughGridCalculator.calculateBestPathThroughGrid(grid, MAX_TOTAL);
        return outputFormatter.getOutput(bestPath, numberOfColumns);
    }
}
