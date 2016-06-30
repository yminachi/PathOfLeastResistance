package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.apache.commons.math.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BestPathThroughGridCalculatorTest {
	private static final double MAX_TOTAL = 50;
	private final static double[] COLUMN_1 = {1}, COLUMN_2 = {2}, COLUMN_3 = {3};

	private BestPathThroughGridCalculator underTest;

	@Mock
	private BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator;

	@Mock
	private MinimumPathPicker minimumPathPicker;

	@Mock
	private InitialColumnPathsBuilder initialColumnPathsBuilder;

	@Mock
	private RealMatrix grid;

	@Mock
	private Path expectedPath;

	@Mock
	private Map<Integer, Path> initialEmptyPaths, bestColumnPaths1, bestColumnPaths2, bestColumnPaths3;

	@Mock
	private Collection<Path> paths1, paths2, paths3;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		underTest = new BestPathThroughGridCalculator(bestPathsThroughColumnCalculator, initialColumnPathsBuilder, minimumPathPicker);
	}

	@Test
	public void shouldGetBestPathForGrid() {
		stubColumnPaths();
		when(minimumPathPicker.pickMinimumPath(paths3)).thenReturn(expectedPath);

		Path result = underTest.calculateBestPathThroughGrid(grid, MAX_TOTAL);

		assertThat(result, is(expectedPath));
	}

	@Test
	public void shouldReturnIncompletePathWhenColumnHasNoActivePaths() {
		stubColumnPaths();
		when(bestPathsThroughColumnCalculator.getBestPathsForColumn(COLUMN_3, bestColumnPaths2, MAX_TOTAL)).thenReturn(Collections.<Integer, Path>emptyMap());
		when(minimumPathPicker.pickMinimumPath(paths2)).thenReturn(expectedPath);

		Path result = underTest.calculateBestPathThroughGrid(grid, MAX_TOTAL);

		assertThat(result, is(expectedPath));
	}

	private void stubColumnPaths() {
		when(grid.getColumnDimension()).thenReturn(3);
		when(initialColumnPathsBuilder.buildInitialColumnPaths(3)).thenReturn(initialEmptyPaths);

		stubColumnPath(1, COLUMN_1, paths2, initialEmptyPaths, bestColumnPaths1);
		stubColumnPath(2, COLUMN_2, paths2, bestColumnPaths1, bestColumnPaths2);
		stubColumnPath(3, COLUMN_3, paths3, bestColumnPaths2, bestColumnPaths3);
	}

	private void stubColumnPath(int index, double[] column, Collection<Path> paths, Map<Integer, Path> previousPaths, Map<Integer, Path> currentPaths) {
		when(grid.getColumn(index)).thenReturn(column);
		when(bestPathsThroughColumnCalculator.getBestPathsForColumn(column, previousPaths, MAX_TOTAL)).thenReturn(currentPaths);
		when(currentPaths.values()).thenReturn(paths);
	}
}
