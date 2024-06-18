package org.example;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoundingBoxServiceTest {
    @Test
    public void testFindBoundingBoxesSimple() {
        boolean[][] array = TestResources.simpleArray;
        List<BoundingBox> boundingBoxes = BoundingBoxService.findBoundingBoxes(array);

        List<BoundingBox> expectedBoundingBoxes = new LinkedList<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(1, 1), new Node(2,2)));

        assertEquals(expectedBoundingBoxes, boundingBoxes);
    }

    @Test
    public void testFindBoundingBoxesGroups() {
        boolean[][] array = TestResources.groupsArray;
        List<BoundingBox> boundingBoxes = BoundingBoxService.findBoundingBoxes(array);

        List<BoundingBox> expectedBoundingBoxes = new LinkedList<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(0, 0), new Node(1,1)));
        expectedBoundingBoxes.add(new BoundingBox(new Node(4, 1), new Node(9,3)));
        expectedBoundingBoxes.add(new BoundingBox(new Node(8, 0), new Node(11, 2)));

        assertEquals(expectedBoundingBoxes, boundingBoxes);
    }
}