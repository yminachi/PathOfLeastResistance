package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.AdjacentCellPathPicker;
import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.BestPathThroughCellCalculator;
import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.BestPathThroughGridCalculator;
import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.BestPathsThroughColumnCalculator;
import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.GridConverter;
import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.InitialColumnPathsBuilder;
import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.MinimumPathPicker;
import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.OutputFormatter;

public class PathOfLeastResistanceCalculatorFactory {

    public PathOfLeastResistanceCalculator createPathOfLeastResistanceCalculator() {
        AdjacentCellPathPicker adjacentCellPathPicker = new AdjacentCellPathPicker();
        MinimumPathPicker minimumPathPicker = new MinimumPathPicker();
        GridConverter gridConverter = new GridConverter();
        InitialColumnPathsBuilder initialColumnPathsBuilder = new InitialColumnPathsBuilder();
        BestPathThroughCellCalculator bestPathThroughCellCalculator = new BestPathThroughCellCalculator(minimumPathPicker);
        OutputFormatter outputFormatter = new OutputFormatter();
        BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator = new BestPathsThroughColumnCalculator(adjacentCellPathPicker, bestPathThroughCellCalculator);
        BestPathThroughGridCalculator bestPathThroughGridCalculator = new BestPathThroughGridCalculator(bestPathsThroughColumnCalculator, initialColumnPathsBuilder, minimumPathPicker);

        return new PathOfLeastResistanceCalculator(bestPathThroughGridCalculator, gridConverter, outputFormatter);
    }
}
