package com.yminachi.pathofleastresistance;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class PathOfLeastResistanceCalculatorFactoryTest {
    @Test
    public void shouldReturnPathOfLeastResistanceCalculator(){
        PathOfLeastResistanceCalculatorFactory underTest = new PathOfLeastResistanceCalculatorFactory();
        assertThat(underTest.createPathOfLeastResistanceCalculator(), Matchers.isA(PathOfLeastResistanceCalculator.class));
    }
}
