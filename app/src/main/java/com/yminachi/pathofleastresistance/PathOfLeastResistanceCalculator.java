package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.leastresistancepathcalculator.AdjacentCellPathPicker;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathThroughCellCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathThroughGridCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathsThroughColumnCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.GridConverter;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.InitialColumnPathsBuilder;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.MinimumPathPicker;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.OutputFormatter;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.Path;

import org.apache.commons.math.linear.RealMatrix;

public class PathOfLeastResistanceCalculator {

    private AdjacentCellPathPicker adjacentCellPathPicker = new AdjacentCellPathPicker();
    private MinimumPathPicker minimumPathPicker = new MinimumPathPicker();
    private BestPathThroughCellCalculator bestPathThroughCellCalculator = new BestPathThroughCellCalculator(minimumPathPicker);
    private BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator = new BestPathsThroughColumnCalculator(adjacentCellPathPicker, bestPathThroughCellCalculator);
    private InitialColumnPathsBuilder initialColumnPathsBuilder = new InitialColumnPathsBuilder();
    private BestPathThroughGridCalculator bestPathThroughGridCalculator = new BestPathThroughGridCalculator(bestPathsThroughColumnCalculator, initialColumnPathsBuilder, minimumPathPicker);
    private GridConverter gridConverter = new GridConverter();
    private OutputFormatter outputFormatter = new OutputFormatter();

    public String calculatePathOfLeastResistance(String input){
        RealMatrix grid = gridConverter.convertToGrid(input);
        int numberOfColumns = grid.getColumnDimension();
        Path bestPath = bestPathThroughGridCalculator.calculateBestPathThroughGrid(grid, 50);
        return outputFormatter.getOutput(bestPath, numberOfColumns);
    }
}
