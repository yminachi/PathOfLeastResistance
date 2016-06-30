package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.bestpathcalculationhelpers.GridConverter;

import org.apache.commons.math.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GridConverterTest {
    private GridConverter underTest;

    @Before
    public void setup(){
        underTest = new GridConverter();
    }

    @Test
    public void shouldConstructGrid(){
        String input = "1 2\n3 4";
        double[][] expectedOutput = {
                {1,2},
                {3,4}
        };

        RealMatrix result = underTest.convertToGrid(input);
        assertThat(result.getData(), is(expectedOutput));
    }
}
