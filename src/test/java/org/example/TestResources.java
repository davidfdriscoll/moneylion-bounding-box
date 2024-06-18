package org.example;

public class TestResources {
    /**
     * corresponds to
     *     ----
     *     -**-
     *     -**-
     *     ----
     */
    private static final boolean[][] simpleArray = new boolean[][]{
            {false, false, false, false},
            {false, true, true, false},
            {false, true, true, false},
            {false, false, false, false}
    };

    private static boolean[][] copyArray(boolean[][] array) {
        boolean[][] copy = new boolean[array.length][];
        for(int i = 0; i < array.length; i++)
            copy[i] = array[i].clone();
        return copy;
    }

    public static boolean[][] getSimpleArray() {
        return copyArray(simpleArray);
    }

    /**
     * corresponds to
     * 012345678901
     * **-------*** 0
     * -*--**--***- 1
     * -----***--** 2
     * -------***-- 3
     *
     * bounding boxes:
     * (0,0), (1,1)
     * (4,1), (9,3)
     * (8,0), (11,2)
     */
    private static final boolean[][] groupsArray = new boolean[][]{
            {true, true, false, false, false, false, false, false, false, true, true, true},
            {false, true, false, false, true, true, false, false, true, true, true, false},
            {false, false, false, false, false, true, true, true, false, false, true, true},
            {false, false, false, false, false, false, false, true, true, true, false, false}
    };

    public static boolean[][] getGroupsArray() {
        return copyArray(groupsArray);
    }
}
