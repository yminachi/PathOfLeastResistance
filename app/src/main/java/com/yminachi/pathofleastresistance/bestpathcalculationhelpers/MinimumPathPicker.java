package com.yminachi.pathofleastresistance.bestpathcalculationhelpers;


import com.yminachi.pathofleastresistance.Path;

import java.util.Collection;
import java.util.Comparator;

import static java.util.Collections.min;

public class MinimumPathPicker {
    public Path pickMinimumPath(Collection<Path> paths){
        return min(paths, pathComparator);
    }

    private Comparator<Path> pathComparator = new Comparator<Path>() {
        @Override
        public int compare(Path path1, Path path2) {
            return (int)(path1.getPathTotal() - path2.getPathTotal());
        }
    };
}
