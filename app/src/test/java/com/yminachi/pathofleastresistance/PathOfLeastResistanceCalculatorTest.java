package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.BestPathThroughGridCalculator;
import com.yminachi.pathofleastresistance.ioformatters.BadGridException;
import com.yminachi.pathofleastresistance.ioformatters.GridConverter;
import com.yminachi.pathofleastresistance.ioformatters.OutputFormatter;

import org.apache.commons.math.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class PathOfLeastResistanceCalculatorTest {
    private static final String INPUT = "FOO";
    private static final int NUMBER_OF_COLUMNS = 5;
    private static final int MAX_TOTAL = 50;
    private static final String EXPECTED_OUTPUT = "bar";

    private PathOfLeastResistanceCalculator underTest;

    @Mock
    private OutputFormatter outputFormatter;

    @Mock
    private GridConverter gridConverter;

    @Mock
    private BestPathThroughGridCalculator bestPathThroughGridCalculator;

    @Mock
    private RealMatrix grid;

    @Mock
    private Path path;

    @Before
    public void setup() throws BadGridException {
        MockitoAnnotations.initMocks(this);

        underTest = new PathOfLeastResistanceCalculator(bestPathThroughGridCalculator, gridConverter, outputFormatter);

        when(gridConverter.convertToGrid(INPUT)).thenReturn(grid);
        when(grid.getColumnDimension()).thenReturn(NUMBER_OF_COLUMNS);
        when(bestPathThroughGridCalculator.calculateBestPathThroughGrid(grid, MAX_TOTAL )).thenReturn(path);
        when(outputFormatter.getOutput(path, NUMBER_OF_COLUMNS)).thenReturn(EXPECTED_OUTPUT);
    }

    @Test
    public void shouldReturnExpectedOutput(){
        assertThat(underTest.calculatePathOfLeastResistance(INPUT), is(EXPECTED_OUTPUT));
    }
}
