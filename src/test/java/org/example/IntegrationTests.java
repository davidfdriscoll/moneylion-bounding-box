package org.example;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {
    @Test
    public void runIntegrationTests() throws FileNotFoundException {
        Map<String, Set<BoundingBox>> tests = new HashMap<>();

        // groups.txt
        Set<BoundingBox> groups = new HashSet<>();
        groups.add(new BoundingBox(new Node(0, 0), new Node(1,1)));
        tests.put("groups.txt", groups);

        // simple.txt
        Set<BoundingBox> simple = new HashSet<>();
        simple.add(new BoundingBox(new Node(1, 1), new Node(2,2)));
        tests.put("simple.txt", simple);

        // empty.txt
        Set<BoundingBox> empty = new HashSet<>();
        tests.put("empty.txt", empty);

        // snakes.txt
        Set<BoundingBox> snakes = new HashSet<>();
        tests.put("snakes.txt", snakes);

        // ontop.txt
        Set<BoundingBox> ontop = new HashSet<>();
        ontop.add(new BoundingBox(new Node(1, 0), new Node(10,3)));
        tests.put("ontop.txt", ontop);

        // singleasterisk.txt
        Set<BoundingBox> singleAsterisk = new HashSet<>();
        singleAsterisk.add(new BoundingBox(new Node(0, 0), new Node(0,0)));
        tests.put("singleasterisk.txt", singleAsterisk);

        // bigl.txt
        Set<BoundingBox> bigL = new HashSet<>();
        bigL.add(new BoundingBox(new Node(1, 15), new Node(19,22)));
        tests.put("bigl.txt", bigL);

        // cross.txt
        Set<BoundingBox> cross = new HashSet<>();
        cross.add(new BoundingBox(new Node(12, 10), new Node(21,14)));
        tests.put("cross.txt", cross);

        // multiple_asterisks.txt
        Set<BoundingBox> multipleAsterisks = new HashSet<>();
        multipleAsterisks.add(new BoundingBox(new Node(0, 0), new Node(0,0)));
        multipleAsterisks.add(new BoundingBox(new Node(2, 2), new Node(2,2)));
        multipleAsterisks.add(new BoundingBox(new Node(0, 2), new Node(0,2)));
        multipleAsterisks.add(new BoundingBox(new Node(2, 0), new Node(2,0)));
        multipleAsterisks.add(new BoundingBox(new Node(1, 1), new Node(1,1)));
        tests.put("multiple_asterisks.txt", multipleAsterisks);

        // overlapping_groups.txt
        Set<BoundingBox> overlappingGroups = new HashSet<>();
        tests.put("overlapping_groups.txt", overlappingGroups);

        // adjacent_groups.txt
        Set<BoundingBox> adjacentGroups = new HashSet<>();
        adjacentGroups.add(new BoundingBox(new Node(0, 0), new Node(1,1)));
        adjacentGroups.add(new BoundingBox(new Node(2, 2), new Node(3,3)));
        adjacentGroups.add(new BoundingBox(new Node(4, 0), new Node(5,1)));
        tests.put("adjacent_groups.txt", adjacentGroups);

        // complex.txt
        Set<BoundingBox> complex = new HashSet<>();
        tests.put("complex.txt", complex);

        // protruding.txt
        Set<BoundingBox> protruding = new HashSet<>();
        tests.put("protruding.txt", protruding);

        // diagonals.txt
        Set<BoundingBox> diagonals = new HashSet<>();
        diagonals.add(new BoundingBox(new Node(11, 0), new Node(12,1)));
        tests.put("diagonals.txt", diagonals);

        for (Map.Entry<String, Set<BoundingBox>> test: tests.entrySet()) {
            String filename = "src/test/resources/" + test.getKey();
            Set<BoundingBox> expectedBoxes = test.getValue();
            boolean[][] grid = IOReader.parseFromInputStream(new FileInputStream(filename));
            Set<BoundingBox> foundBoxes = BoundingBoxService.findBiggestNonOverlappingBoundingBox(grid);
            assertEquals("test failed on " + test.getKey(), expectedBoxes, foundBoxes);
        }
    }
}
