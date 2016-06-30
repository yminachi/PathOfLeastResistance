package com.yminachi.pathofleastresistance.ioformatters;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

import static java.lang.Double.parseDouble;

public class GridConverter {
    public RealMatrix convertToGrid(String input) {
        String[] rows = input.split("\n");

        double[][] grid = new double[rows.length][];

        for (int rowNumber = 0; rowNumber < rows.length; rowNumber++) {
            grid[rowNumber] = convertRowToArray(rows[rowNumber]);
        }

        return new RealMatrixImpl(grid);
    }

    private double[] convertRowToArray(String row) {
        String[] cellValues = row.split(" ");
        double[] rowCellValues = new double[cellValues.length];

        for (int columnIndex = 0; columnIndex < cellValues.length; columnIndex++) {
            rowCellValues[columnIndex] = parseDouble(cellValues[columnIndex]);
        }

        return rowCellValues;
    }
}
