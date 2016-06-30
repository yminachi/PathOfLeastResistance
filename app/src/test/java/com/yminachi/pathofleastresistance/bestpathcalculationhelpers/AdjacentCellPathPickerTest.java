package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class AdjacentCellPathPickerTest {
	private static final int CURRENT_ROW = 5;
	private static final int COLUMN_SIZE = 10;

	private AdjacentCellPathPicker underTest;

	@Mock
	private Map<Integer, Path> lastColumnsPaths;

	@Mock
	private Path path;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		when(lastColumnsPaths.size()).thenReturn(COLUMN_SIZE);

		underTest = new AdjacentCellPathPicker();
	}

	@Test
	public void shouldPickPreviousColumn() {
		when(lastColumnsPaths.get(CURRENT_ROW)).thenReturn(path);

		List<Path> result = underTest.pickAdjacentPaths(lastColumnsPaths, CURRENT_ROW);

		assertThat(result, hasItem(path));
	}

	@Test
	public void shouldPickUpwardDiagonal() {
		when(lastColumnsPaths.get(CURRENT_ROW - 1)).thenReturn(path);

		List<Path> result = underTest.pickAdjacentPaths(lastColumnsPaths, CURRENT_ROW);

		assertThat(result, hasItem(path));
	}

	@Test
	public void shouldPickDownwardDiagonal() {
		when(lastColumnsPaths.get(CURRENT_ROW + 1)).thenReturn(path);

		List<Path> result = underTest.pickAdjacentPaths(lastColumnsPaths, CURRENT_ROW);

		assertThat(result, hasItem(path));
	}

	@Test
	public void shouldWrapDownward() {
		when(lastColumnsPaths.get(-1)).thenThrow(IndexOutOfBoundsException.class);
		when(lastColumnsPaths.get(COLUMN_SIZE - 1)).thenReturn(path);

		List<Path> result = underTest.pickAdjacentPaths(lastColumnsPaths, 0);

		assertThat(result, hasItem(path));
	}

	@Test
	public void shouldWrapUpward() {
		when(lastColumnsPaths.get(COLUMN_SIZE)).thenThrow(IndexOutOfBoundsException.class);
		when(lastColumnsPaths.get(0)).thenReturn(path);

		List<Path> result = underTest.pickAdjacentPaths(lastColumnsPaths, COLUMN_SIZE - 1);

		assertThat(result, hasItem(path));
	}

	@Test
	public void shouldNotIncludeNulls() {
		when(lastColumnsPaths.get(CURRENT_ROW)).thenReturn(null);
		when(lastColumnsPaths.get(CURRENT_ROW - 1)).thenReturn(null);
		when(lastColumnsPaths.get(CURRENT_ROW + 1)).thenReturn(null);

		List<Path> result = underTest.pickAdjacentPaths(lastColumnsPaths, CURRENT_ROW);

		assertThat(result, is(Collections.<Path>emptyList()));
	}
}
