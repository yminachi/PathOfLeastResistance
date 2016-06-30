package com.yminachi.pathofleastresistance.ioformatters;

import org.apache.commons.math.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GridConverterTest {
    private static final int GRID_MAX_HEIGHT = 10;
    private static final int GRID_MAX_WIDTH = 100;
    private static final int GRID_MIN_HEIGHT = 1;
    private static final int GRID_MIN_WIDTH = 5;
    private static final int VALID_WIDTH_OR_HEIGHT = 4;

    private GridConverter underTest;

    @Before
    public void setup(){
        underTest = new GridConverter();
    }

    @Test
    public void shouldConstructGrid() throws BadGridException {
        String input = "1 2 3 4 5\n3 4 6 4 5";
        double[][] expectedOutput = {
                {1,2,3,4,5},
                {3,4,6,4,5}
        };

        RealMatrix result = underTest.convertToGrid(input);
        assertThat(result.getData(), is(expectedOutput));
    }

    @Test(expected = BadGridException.class)
    public void shouldThrowExceptionWhenGridIsNotRectangle() throws BadGridException {
        String input = "1\n3 6";

        underTest.convertToGrid(input);
    }

    @Test(expected = BadGridException.class)
    public void shouldThrowExceptionWhenGridHasNonNumbers() throws BadGridException{
        String input = "1 t\n3 6";

        underTest.convertToGrid(input);
    }

    @Test(expected = BadGridException.class)
    public void shouldThrowExceptionWhenHeightTooSmall() throws BadGridException{
        String input = constructDummyGridOfDimensions(GRID_MIN_HEIGHT - 1, VALID_WIDTH_OR_HEIGHT);

        underTest.convertToGrid(input);
    }

    @Test(expected = BadGridException.class)
    public void shouldThrowExceptionWhenWidthTooSmall() throws BadGridException{
        String input = constructDummyGridOfDimensions(GRID_MIN_WIDTH - 1, VALID_WIDTH_OR_HEIGHT);

        underTest.convertToGrid(input);
    }

    @Test(expected = BadGridException.class)
    public void shouldThrowExceptionWhenHeightTooBig() throws BadGridException{
        String input = constructDummyGridOfDimensions(GRID_MAX_HEIGHT + 1, VALID_WIDTH_OR_HEIGHT);

        underTest.convertToGrid(input);
    }

    @Test(expected = BadGridException.class)
    public void shouldThrowExceptionWhenWidthTooBig() throws BadGridException{
        String input = constructDummyGridOfDimensions(VALID_WIDTH_OR_HEIGHT, GRID_MAX_WIDTH + 1);

        underTest.convertToGrid(input);
    }

    private String constructDummyGridOfDimensions(int height, int width){
        String gridString = "";
        for (int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                gridString += "1" + " ";
            }
            gridString = gridString.substring(0, gridString.length() - 1) + "\n";
        }
        return gridString;
    }
}
