package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InitialColumnPathsBuilderTest {
	private static final int NUMBER_OF_ROWS = 2;
	private InitialColumnPathsBuilder underTest;

	@Before
	public void setup() {
		underTest = new InitialColumnPathsBuilder();
	}

	@Test
	public void shouldBuildEmptyPaths() {
		Map<Integer, Path> result = underTest.buildInitialColumnPaths(NUMBER_OF_ROWS);
		assertPathEmpty(result, 0);
		assertPathEmpty(result, 1);
	}

	private void assertPathEmpty(Map<Integer, Path> result, int index){
		assertThat(result.get(index).getPathTotal(), is(0d));
		assertThat(result.get(index).getRowsInPath(), is(Collections.<Integer>emptyList()));
	}
}
