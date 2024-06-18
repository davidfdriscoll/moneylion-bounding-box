package org.example;

public class TestResources {
    /**
     * corresponds to
     *     ----
     *     -**-
     *     -**-
     *     ----
     */
    public static boolean[][] simpleArray = new boolean[][]{
            {false, false, false, false},
            {false, true, true, false},
            {false, true, true, false},
            {false, false, false, false}
    };

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
    public static boolean[][] groupsArray = new boolean[][]{
            {true, true, false, false, false, false, false, false, false, true, true, true},
            {false, true, false, false, true, true, false, false, true, true, true, false},
            {false, false, false, false, false, true, true, true, false, false, true, true},
            {false, false, false, false, false, false, false, true, true, true, false, false}
    };
}
