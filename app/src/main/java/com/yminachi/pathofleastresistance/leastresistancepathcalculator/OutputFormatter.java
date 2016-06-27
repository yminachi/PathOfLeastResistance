package com.yminachi.pathofleastresistance.leastresistancepathcalculator;

public class OutputFormatter {
    private static final String YES = "Yes";
    private static final String NO = "No";

    public String getOutput(Path path, int numberOfColumns) {
        String output = path.getRowsInPath().size() == numberOfColumns ? YES : NO;
        output += "\n" + (int) path.getPathTotal() + "\n";
        for (int row : path.getRowsInPath()) {
            output += row + " ";
        }
        //remove trailing space
        output = output.substring(0, output.length() - 1);

        return output;
    }
}
