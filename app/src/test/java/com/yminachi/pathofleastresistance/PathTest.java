package com.yminachi.pathofleastresistance;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PathTest {
    private static final double EXPECTED_TOTAL = 5;

    private Path underTest;

    @Mock
    private List<Integer> expectedRowsInPath;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        underTest = new Path(expectedRowsInPath, EXPECTED_TOTAL);
    }

    @Test
    public void shouldGetPathTotal(){
        assertThat(underTest.getPathTotal(), is(EXPECTED_TOTAL));
    }

    @Test
    public void shouldGetRowsInPath(){
        assertThat(underTest.getRowsInPath(), is(expectedRowsInPath));
    }
}
