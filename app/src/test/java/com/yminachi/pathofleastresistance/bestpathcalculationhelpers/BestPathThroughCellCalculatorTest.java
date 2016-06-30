package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Arrays.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BestPathThroughCellCalculatorTest {
    private static final int PREVIOUS_ROW_1 = 3;
    private static final int PREVIOUS_ROW_2 = 4;
    private static final double PREVIOUS_TOTAL = 20;

    private static final int ROW = 5;
    private static final double VALUE = 10;

    private static final List<Integer> EXPECTED_ROWS_IN_PATH = asList(PREVIOUS_ROW_1, PREVIOUS_ROW_2, ROW);

    private BestPathThroughCellCalculator underTest;

    @Mock
    private List<Path> previousAdjacentPaths;

    @Mock
    private MinimumPathPicker minimumPathPicker;

    @Mock
    private Path path;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        underTest = new BestPathThroughCellCalculator(minimumPathPicker);

        when(minimumPathPicker.pickMinimumPath(previousAdjacentPaths)).thenReturn(path);
        when(path.getRowsInPath()).thenReturn(asList(PREVIOUS_ROW_1, PREVIOUS_ROW_2));
        when(path.getPathTotal()).thenReturn(PREVIOUS_TOTAL);
    }

    @Test
    public void shouldHaveExpectedRowsInPath(){
        Path result = underTest.getBestPathThroughCell(previousAdjacentPaths, ROW, VALUE);

        assertThat(result.getRowsInPath(), is(EXPECTED_ROWS_IN_PATH));
    }

    @Test
    public void shouldHaveExpectedTotal(){
        Path result = underTest.getBestPathThroughCell(previousAdjacentPaths, ROW, VALUE);

        assertThat(result.getPathTotal(), is(PREVIOUS_TOTAL + VALUE));
    }
}
