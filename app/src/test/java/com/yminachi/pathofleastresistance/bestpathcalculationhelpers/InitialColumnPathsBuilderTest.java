package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InitialColumnPathsBuilderTest {
	private static double[] firstColumn = {1, 2};

	private InitialColumnPathsBuilder underTest;

	@Before
	public void setup() {
		underTest = new InitialColumnPathsBuilder();
	}

	@Test
	public void shouldBuildWithExpectedFirstPath() {
		Map<Integer, Path> result = underTest.buildInitialColumnPaths(firstColumn);

		assertThat(result.get(0).getPathTotal(), is(firstColumn[0]));
		assertThat(result.get(0).getRowsInPath(), is(singletonList(0)));
	}

	@Test
	public void shouldBuildWithExpectedSecondPath() {
		Map<Integer, Path> result = underTest.buildInitialColumnPaths(firstColumn);

		assertThat(result.get(1).getPathTotal(), is(firstColumn[1]));
		assertThat(result.get(1).getRowsInPath(), is(singletonList(1)));
	}
}
