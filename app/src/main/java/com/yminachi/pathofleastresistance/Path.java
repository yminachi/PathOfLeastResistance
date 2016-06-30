package com.yminachi.pathofleastresistance;

import java.util.List;

public class Path {
	private double pathTotal;
	private List<Integer> rowsInPath;

	public Path(List<Integer> rowsInPath, double pathTotal) {
		this.pathTotal = pathTotal;
		this.rowsInPath = rowsInPath;
	}

	public double getPathTotal() {
		return pathTotal;
	}

	public List<Integer> getRowsInPath() {
		return rowsInPath;
	}
}
