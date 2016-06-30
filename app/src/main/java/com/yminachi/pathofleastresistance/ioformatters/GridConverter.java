package com.yminachi.pathofleastresistance.ioformatters;

import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

import static java.lang.Double.parseDouble;

public class GridConverter {
    private static final int GRID_MAX_HEIGHT = 10;
    private static final int GRID_MAX_WIDTH = 100;

    public RealMatrix convertToGrid(String input) throws BadGridException {
        String[] rows = input.split("\n");
        throwExceptionWhenGridHasNoRows(rows);

        int gridWidth = rows[0].split(" ").length;
        throwExceptionWhenGridIsInvalidSize(rows.length, gridWidth);

        double[][] grid = new double[rows.length][];

        for (int rowNumber = 0; rowNumber < rows.length; rowNumber++) {
            String[] cellValues = rows[rowNumber].split(" ");
            throwExceptionWhenRowIsWrongSize(gridWidth, cellValues.length);
            grid[rowNumber] = convertRowToArray(cellValues);
        }

        return new RealMatrixImpl(grid);
    }

    private double[] convertRowToArray(String[] cellValues) throws BadGridException {
        double[] rowCellValues = new double[cellValues.length];

        for (int columnIndex = 0; columnIndex < cellValues.length; columnIndex++) {
            rowCellValues[columnIndex] = parseDoubleOrThrowException(cellValues[columnIndex]);
        }

        return rowCellValues;
    }

    private double parseDoubleOrThrowException(String value) throws BadGridException {
        try{
            return parseDouble(value);
        } catch (NumberFormatException e){
            throw new BadGridException();
        }
    }

    private void throwExceptionWhenRowIsWrongSize(int gridWidth, int rowWidth) throws BadGridException {
        if (gridWidth != rowWidth){
            throw new BadGridException();
        }
    }

    private void throwExceptionWhenGridHasNoRows(String[] rows) throws BadGridException {
        if (rows.length == 0){
            throw new BadGridException();
        }
    }

    private void throwExceptionWhenGridIsInvalidSize(int height, int width) throws BadGridException {
        if (height > GRID_MAX_HEIGHT || width > GRID_MAX_WIDTH){
            throw new BadGridException();
        }
    }
}
