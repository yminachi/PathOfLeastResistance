package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;

import com.yminachi.pathofleastresistance.Path;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class MinimumPathPickerTest {
    private static final double LOW_TOTAL = 5;
    private static final double HIGH_TOTAL = 10;

    private MinimumPathPicker underTest;

    @Mock
    private Path path1;

    @Mock
    private Path path2;

    @Mock
    private Path path3;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        underTest = new MinimumPathPicker();

        when(path1.getPathTotal()).thenReturn(HIGH_TOTAL);
        when(path2.getPathTotal()).thenReturn(HIGH_TOTAL);
        when(path3.getPathTotal()).thenReturn(HIGH_TOTAL);
    }

    @Test
    public void shouldPickMinimumPath(){
        when(path1.getPathTotal()).thenReturn(LOW_TOTAL);

        assertThat(underTest.pickMinimumPath(asList(path1, path2, path3)), is(path1));
    }

    @Test
    public void shouldPickSecondMinimumPath(){
        when(path2.getPathTotal()).thenReturn(LOW_TOTAL);

        assertThat(underTest.pickMinimumPath(asList(path1, path2, path3)), is(path2));
    }

    @Test
    public void shouldPickThirdMinimumPath(){
        when(path3.getPathTotal()).thenReturn(LOW_TOTAL);

        assertThat(underTest.pickMinimumPath(asList(path1, path2, path3)), is(path3));
    }
}
