package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathThroughGridCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathsThroughColumnCalculator;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.GridConverter;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.MinimumPathPicker;
import com.yminachi.pathofleastresistance.leastresistancepathcalculator.OutputFormatter;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

public class PathOfLeastResistanceFunctionalTest {
    private PathOfLeastResistanceCalculator calculator;

    @Before
    public void setup() {
        calculator = getPathOfLeastResistanceCalculator();
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

    //see comment in PathOfLeastResistance.java
    private PathOfLeastResistanceCalculator getPathOfLeastResistanceCalculator(){
        MinimumPathPicker minimumPathPicker = new MinimumPathPicker();
        GridConverter gridConverter = new GridConverter();
        OutputFormatter outputFormatter = new OutputFormatter();
        BestPathsThroughColumnCalculator bestPathsThroughColumnCalculator = new BestPathsThroughColumnCalculator(minimumPathPicker);
        BestPathThroughGridCalculator bestPathThroughGridCalculator = new BestPathThroughGridCalculator(bestPathsThroughColumnCalculator, minimumPathPicker);

        return new PathOfLeastResistanceCalculator(bestPathThroughGridCalculator, gridConverter, outputFormatter);
    }
}
