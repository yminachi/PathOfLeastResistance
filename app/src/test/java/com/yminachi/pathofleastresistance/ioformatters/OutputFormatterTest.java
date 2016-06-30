package com.yminachi.pathofleastresistance.ioformatters;

import com.yminachi.pathofleastresistance.Path;
import com.yminachi.pathofleastresistance.ioformatters.OutputFormatter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.lang.Double.parseDouble;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class OutputFormatterTest {
    private static final int NUMBER_OF_COLUMNS = 5;
    private static final List<Integer> ROWS_IN_PATH = asList(1, 2, 3, 4, 5);
    private static final String YES = "Yes";
    private static final String NO = "No";

    private OutputFormatter underTest;

    @Mock
    private Path path;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        underTest = new OutputFormatter();
    }

    @Test
    public void shouldHaveFirstLineAsYesWhenGridTraversed() {
        when(path.getRowsInPath()).thenReturn(ROWS_IN_PATH);

        String result = underTest.getOutput(path, NUMBER_OF_COLUMNS);

        assertThat(result.split("\n")[0], is(YES));
    }

    @Test
    public void shouldHaveSecondLineAsTotal() {
        String expectedTotal = "5";
        when(path.getPathTotal()).thenReturn(parseDouble(expectedTotal));

        String result = underTest.getOutput(path, NUMBER_OF_COLUMNS);

        assertThat(result.split("\n")[1], is(expectedTotal));
    }

    @Test
    public void shouldHaveThirdLineAsPath() {
        //output has a starting index of 1
        String expectedRowsInPath = "2 3 4 5 6";
        when(path.getRowsInPath()).thenReturn(ROWS_IN_PATH);

        String result = underTest.getOutput(path, NUMBER_OF_COLUMNS);

        assertThat(result.split("\n")[2], is(expectedRowsInPath));
    }

    @Test
    public void shouldHaveFirstLineAsNoWhenGridNotTraversed() {
        when(path.getRowsInPath()).thenReturn(asList(1,2,3));

        String result = underTest.getOutput(path, NUMBER_OF_COLUMNS);

        assertThat(result.split("\n")[0], is(NO));
    }
}
