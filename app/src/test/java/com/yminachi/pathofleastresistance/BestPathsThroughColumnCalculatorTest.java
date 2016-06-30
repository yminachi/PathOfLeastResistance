package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathsThroughColumnCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.MinimumPathPicker;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BestPathsThroughColumnCalculatorTest {
    private static final double[] COLUMN = {10, 20, 30};

    private static final double MAX_TOTAL = 50;

    private BestPathsThroughColumnCalculator underTest;

    @Mock
    private MinimumPathPicker minimumPathPicker;

    @Mock
    private Path bestPath;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        underTest = new BestPathsThroughColumnCalculator(minimumPathPicker);
        when(minimumPathPicker.pickMinimumPath(expectedPaths().values())).thenReturn(bestPath);
    }

    private Map<Integer, Path> expectedPaths(){
        Map<Integer, Path> paths = new HashMap<>();
        paths.put(1,new Path(asList(1, 10), 11));
        paths.put(2,new Path(asList(2, 20), 22));
        paths.put(3,new Path(asList(3, 30), 33));
        return paths;
    }

    private Map<Integer, Path> previousPaths(){
        Map<Integer, Path> paths = new HashMap<>();
        paths.put(1,new Path(singletonList(1), 1));
        paths.put(2,new Path(singletonList(2), 2));
        paths.put(3,new Path(singletonList(3), 3));
        return paths;
    }

    @Test
    public void shouldGetBestPathsThroughColumn(){
        assertThat(underTest.getBestPathsForColumn(COLUMN, previousPaths(), MAX_TOTAL), is(expectedPaths()));
    }

    @Test
    public void shouldNotSavePathsOverMaxTotal(){
        assertThat(underTest.getBestPathsForColumn(COLUMN, previousPaths(), MAX_TOTAL), is(expectedPaths()));
    }

    @Test
    public void shouldNotPassEmptyAdjacentPaths(){
        underTest.getBestPathsForColumn(COLUMN, Collections.<Integer, Path>emptyMap(), MAX_TOTAL);
    }

}
