package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BestPathsThroughColumnCalculatorTest {
    private static final double[] COLUMN = {10, 20};
    private static final double MAX_TOTAL = 50;

    private BestPathsThroughColumnCalculator underTest;

    @Mock
    private AdjacentCellPathPicker adjacentCellPathPicker;

    @Mock
    private BestPathThroughCellCalculator bestPathThroughCellCalculator;

    @Mock
    private Map<Integer, Path> lastColumnsBestPaths;

    @Mock
    private List<Path> adjacentPaths;

    @Mock
    private List<Path> adjacentPaths2;

    @Mock
    private Path bestPath;

    @Mock
    private Path bestPath2;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        underTest = new BestPathsThroughColumnCalculator(adjacentCellPathPicker, bestPathThroughCellCalculator);
    }

    @Test
    public void shouldGetBestPathsThroughColumn(){
        stubForCell(0, adjacentPaths, bestPath);
        stubForCell(1, adjacentPaths2, bestPath2);

        assertThat(underTest.getBestPathsForColumn(COLUMN, lastColumnsBestPaths, MAX_TOTAL), allOf(hasEntry(0, bestPath), hasEntry(1, bestPath2)));
    }

    @Test
    public void shouldNotSavePathsOverMaxTotal(){
        stubForCell(0, adjacentPaths, bestPath);
        stubForCell(1, adjacentPaths2, bestPath2);
        when(bestPath2.getPathTotal()).thenReturn(MAX_TOTAL + 1);

        assertThat(underTest.getBestPathsForColumn(COLUMN, lastColumnsBestPaths, MAX_TOTAL), not(hasEntry(1, bestPath2)));
    }

    @Test
    public void shouldNotPassEmptyAdjacentPaths(){
        stubForCell(0, adjacentPaths, bestPath);
        when(adjacentCellPathPicker.pickAdjacentPaths(lastColumnsBestPaths, 1)).thenReturn(Collections.<Path>emptyList());
        when(bestPathThroughCellCalculator.getBestPathThroughCell(Collections.<Path>emptyList(), 1, COLUMN[1])).thenThrow(NoSuchElementException.class);

        underTest.getBestPathsForColumn(COLUMN, lastColumnsBestPaths, MAX_TOTAL);
    }


    private void stubForCell(int index, List<Path> adjacentPaths, Path bestPath){
        when(adjacentCellPathPicker.pickAdjacentPaths(lastColumnsBestPaths, index)).thenReturn(adjacentPaths);
        when(bestPathThroughCellCalculator.getBestPathThroughCell(adjacentPaths, index, COLUMN[index])).thenReturn(bestPath);
    }
}
