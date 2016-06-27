package com.yminachi.pathofleastresistance;

import com.yminachi.pathofleastresistance.leastresistancepathcalculator.BestPathThroughGridCalculator;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class BestPathThroughGridCalculatorTest {

    private BestPathThroughGridCalculator underTest = new BestPathThroughGridCalculator();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        underTest = new BestPathThroughGridCalculator();
    }
}
