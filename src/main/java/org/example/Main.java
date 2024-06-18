package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean[][] grid = IOReader.parseFromInputStream(System.in);
        Set<BoundingBox> boxes = BoundingBoxService.findBiggestNonOverlappingBoundingBox(grid);
        for (BoundingBox box: boxes) {
            System.out.println(box);
        }
    }
}