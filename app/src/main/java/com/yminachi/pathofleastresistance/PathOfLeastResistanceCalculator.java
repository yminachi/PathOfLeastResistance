package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.BestPathThroughGridCalculator;
import com.yminachi.pathofleastresistance.ioformatters.BadGridException;
import com.yminachi.pathofleastresistance.ioformatters.GridConverter;
import com.yminachi.pathofleastresistance.ioformatters.OutputFormatter;

import org.apache.commons.math.linear.RealMatrix;

public class PathOfLeastResistanceCalculator {
	private static final int MAX_TOTAL = 50;
	private static final String ERROR_MESSAGE = "Bad Grid Input!!";

	private BestPathThroughGridCalculator bestPathThroughGridCalculator;
	private GridConverter gridConverter;
	private OutputFormatter outputFormatter;

	public PathOfLeastResistanceCalculator() {
		bestPathThroughGridCalculator = new BestPathThroughGridCalculator();
		gridConverter = new GridConverter();
		outputFormatter = new OutputFormatter();
	}

	public void setBestPathThroughGridCalculator(BestPathThroughGridCalculator bestPathThroughGridCalculator) {
		this.bestPathThroughGridCalculator = bestPathThroughGridCalculator;
	}

	public void setGridConverter(GridConverter gridConverter) {
		this.gridConverter = gridConverter;
	}

	public void setOutputFormatter(OutputFormatter outputFormatter) {
		this.outputFormatter = outputFormatter;
	}

	public String calculatePathOfLeastResistance(String input) {
		try {
			RealMatrix grid = gridConverter.convertToGrid(input);
			int numberOfColumns = grid.getColumnDimension();
			Path bestPath = bestPathThroughGridCalculator.calculateBestPathThroughGrid(grid, MAX_TOTAL);
			return outputFormatter.getOutput(bestPath, numberOfColumns);
		} catch (BadGridException e) {
			return ERROR_MESSAGE;
		}
	}
}
