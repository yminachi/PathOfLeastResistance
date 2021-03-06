package com.yminachi.pathofleastresistance.functionaltest;

import com.yminachi.pathofleastresistance.PathOfLeastResistanceCalculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PathOfLeastResistanceFunctionalTest {
	private PathOfLeastResistanceCalculator calculator;

	@Before
	public void setup() {
		calculator = new PathOfLeastResistanceCalculator();
	}

	@Test
	public void testCaseOne() {
		String input =
				"3 4 1 2 8 6\n" +
				"6 1 8 2 7 4\n" +
				"5 9 3 9 9 5\n" +
				"8 4 1 3 2 6\n" +
				"3 7 2 8 6 4";
		String expectedOutput = "Yes\n" +
				"16\n" +
				"1 2 3 4 4 5";

		assertThat(calculator.calculatePathOfLeastResistance(input), is(expectedOutput));
	}

	@Test
	public void testCaseTwo() {
		String input =
				"3 4 1 2 8 6\n" +
						"6 1 8 2 7 4\n" +
						"5 9 3 9 9 5\n" +
						"8 4 1 3 2 6\n" +
						"3 7 2 1 2 3";
		String expectedOutput = "Yes\n" +
				"11\n" +
				"1 2 1 5 4 5";

		String alternateSolution = "Yes\n" +
				"11\n" +
				"1 2 1 5 5 5";

		assertThat(calculator.calculatePathOfLeastResistance(input), anyOf(is(expectedOutput), is(alternateSolution)));
	}

	@Test
	public void testCaseThree() {
		String input =
				"19 10 19 10 19\n" +
				"21 23 20 19 12\n" +
				"20 12 20 11 10";
		String expectedOutput = "No\n" +
				"48\n" +
				"1 1 1";

		assertThat(calculator.calculatePathOfLeastResistance(input), is(expectedOutput));
	}

	@Test
	public void negativeNumbersCase(){
		String input =
				"3 4 1 2 8 6\n" +
				"6 1 8 2 7 4\n" +
				"5 -9 3 -9 -9 5\n" +
				"-8 4 1 3 2 6\n" +
				"3 -7 2 8 6 4";

		String expectedOutput = "Yes\n" +
				"-30\n" +
				"4 3 4 3 3 2";

		assertThat(calculator.calculatePathOfLeastResistance(input), is(expectedOutput));
	}

	@Test
	public void energyCantContinueEvenWhenPathTotalUnderMax(){
		String input =
				"52 -4000 1 2 8 6\n" +
				"65 -1000 8 2 7 4\n" +
				"56 -9000 3 -9 -9 5\n" +
				"82 -4000 1 3 2 6\n" +
				"97 -7000 2 8 6 4";

		String expectedOutput = "No\n" +
				"0";

		assertThat(calculator.calculatePathOfLeastResistance(input), is(expectedOutput));
	}

	@Test
	public void reallyBigTestCase() {
		String input = "5 0 8 6 6 8 0 2 5 3 6 2 9 8 0 7 5 2 8 4 3 3 7 3 6 5 4 8 6 4 4 2 5 3 6 0 3 4 9 7 5 4 6 6 0 1 6 3 3 3 2 6 3 8 5 9 8 7 6 8 6 7 2 8 5 2 8 0 6 1 2 9 9 0 7 9 0 2 9 0 2 2 9 3 4 0 3 4 1 5 6 5 2 0 5 7 0 0 9 5\n" +
				"7 5 6 9 4 8 3 6 8 4 3 4 7 8 6 7 7 0 4 2 5 3 5 7 3 8 3 2 0 2 2 8 8 6 6 9 5 9 6 5 3 0 3 8 0 4 9 2 2 8 6 3 2 1 5 1 1 9 0 6 0 6 4 6 6 0 1 0 0 8 5 9 4 9 0 8 6 3 1 0 8 9 7 2 5 9 2 7 3 7 1 1 0 0 6 7 6 7 8 0\n" +
				"2 4 7 4 3 3 1 4 6 4 1 6 8 1 7 1 1 0 1 6 1 1 5 2 4 1 8 5 4 9 3 6 3 4 8 2 4 3 4 6 6 7 9 6 4 5 9 5 9 5 5 8 0 5 9 4 3 1 7 9 9 3 5 3 2 1 8 1 6 3 8 2 2 2 4 5 6 8 9 4 9 6 0 5 7 9 8 8 9 1 6 7 2 2 2 0 2 4 2 9\n" +
				"7 7 9 3 8 0 5 1 5 3 1 8 5 6 5 0 1 8 2 6 7 7 7 4 2 5 1 7 5 4 5 3 5 5 5 0 1 6 2 1 8 4 5 7 4 9 4 1 7 7 2 0 3 0 6 5 1 4 4 6 1 2 2 9 5 0 6 2 1 6 9 9 3 1 7 9 6 5 6 5 0 3 5 4 4 8 3 1 1 5 0 0 4 4 4 5 4 1 2 0\n" +
				"1 3 7 3 1 7 1 4 0 9 8 5 8 0 5 9 0 7 5 0 3 8 3 1 0 0 6 6 9 4 1 9 1 4 4 6 0 1 7 3 4 1 7 1 4 1 9 0 7 7 5 8 1 0 0 5 3 0 6 3 5 8 5 0 1 0 7 8 8 7 5 4 0 6 6 2 3 5 9 5 0 1 0 9 6 4 4 0 1 1 0 8 3 3 8 7 4 5 5 8\n" +
				"2 3 0 7 6 3 0 4 6 8 0 0 9 5 1 7 6 1 1 5 6 1 2 4 1 8 9 5 4 2 1 9 6 9 7 2 0 5 7 8 6 6 3 2 1 4 3 7 3 0 7 9 7 2 4 6 0 4 9 5 3 2 3 0 4 5 4 4 0 8 2 0 2 8 1 8 7 0 3 3 2 8 9 3 6 1 3 2 3 1 4 2 1 1 1 9 0 0 9 7\n" +
				"0 5 9 1 7 5 3 5 9 3 5 2 2 4 5 6 6 6 7 8 7 7 0 9 0 0 0 5 9 4 7 2 8 4 1 7 5 4 5 8 3 0 2 5 2 8 6 3 8 7 0 1 1 5 8 1 1 4 7 3 7 8 6 3 0 5 5 5 6 8 8 3 6 6 7 5 2 9 8 1 1 3 7 2 8 6 7 1 9 9 1 1 7 5 7 9 0 5 3 6\n" +
				"0 4 0 7 5 8 8 0 5 4 0 6 5 7 9 1 2 1 8 6 7 2 7 7 0 2 6 6 1 2 6 9 8 4 3 5 7 9 9 0 1 6 5 3 9 5 8 3 0 3 8 6 5 3 4 2 9 7 0 5 2 8 7 0 0 9 7 7 6 8 7 2 0 9 0 9 6 9 5 3 1 9 5 4 0 1 5 1 5 7 7 1 9 5 6 8 7 8 7 7\n" +
				"2 2 0 5 0 2 3 6 1 7 8 2 5 1 5 1 0 1 1 7 9 4 2 2 0 0 3 5 0 2 6 3 5 2 7 2 1 8 2 9 7 5 8 0 4 2 8 5 0 3 2 1 7 9 0 3 7 4 0 3 0 9 3 5 2 3 9 4 6 3 3 3 7 6 9 2 2 3 6 6 4 5 6 3 6 0 5 9 3 0 5 0 6 6 1 6 5 4 0 8\n" +
				"6 3 3 7 3 3 9 3 4 8 4 8 1 5 4 5 8 1 3 0 4 7 3 5 9 6 5 8 7 6 8 3 5 8 2 7 7 9 6 8 1 9 9 2 7 4 7 0 1 3 6 9 5 7 6 5 8 1 5 9 6 8 0 2 3 0 3 4 5 0 1 6 9 1 9 8 5 4 7 4 0 4 8 7 7 3 7 9 3 6 5 6 1 8 5 2 5 6 1 3\n";
		input = input.substring(0, input.length() - 1);

		String expectedOutput = "No\n" +
				"49\n" +
				"7 6 6 5 5 4 3 4 5 4 4 5 4 5 4 4 5 6 6 5 5 6 6 5 5 5 4 3 2 2 2 1";

		assertThat(calculator.calculatePathOfLeastResistance(input), is(expectedOutput));
	}

	@Test
	public void testCaseWhereBeginningBestPathIsNotCorrect(){
		String input = "3 4 1 1 0 999\n" +
				"3 7 2 1 2 999\n" +
				"3 7 2 1 2 999\n" +
				"6 0 8 2 7 999\n" +
				"5 9 3 9 9 999\n" +
				"8 4 3 3 2 6\n" +
				"3 7 2 1 2 999\n" +
				"3 7 2 1 2 999\n" +
				"3 7 2 1 2 999\n" +
				"3 7 2 1 2 999";

		String expectedOutput = "Yes\n" +
				"17\n" +
				"3 4 5 6 6 6";

		assertThat(calculator.calculatePathOfLeastResistance(input), is(expectedOutput));
	}
}
