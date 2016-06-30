package com.yminachi.pathofleastresistance.ioformatters;

import com.yminachi.pathofleastresistance.Path;

public class OutputFormatter {
	private static final String YES = "Yes";
	private static final String NO = "No";

	public String getOutput(Path path, int numberOfColumns) {
		String output = path.getRowsInPath().size() == numberOfColumns ? YES : NO;
		output += "\n" + (int) path.getPathTotal() + "\n";
		for (int row : path.getRowsInPath()) {
			output += convertFromZeroToOneIndex(row) + " ";
		}
		output = removeTrialingSpace(output);

		return output;
	}

	private String removeTrialingSpace(String input) {
		return input.substring(0, input.length() - 1);
	}

	private int convertFromZeroToOneIndex(int index) {
		return index + 1;
	}
}
