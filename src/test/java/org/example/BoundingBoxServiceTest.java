package org.example;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BoundingBoxServiceTest {
    @Test
    public void testFindBoundingBoxesSimple() {
        boolean[][] array = TestResources.getSimpleArray();
        List<BoundingBox> boundingBoxes = BoundingBoxService.findBoundingBoxes(array);

        List<BoundingBox> expectedBoundingBoxes = new LinkedList<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(1, 1), new Node(2,2)));

        assertEquals(expectedBoundingBoxes, boundingBoxes);
    }

    @Test
    public void testFindBoundingBoxesGroups() {
        boolean[][] array = TestResources.getGroupsArray();
        List<BoundingBox> boundingBoxes = BoundingBoxService.findBoundingBoxes(array);

        List<BoundingBox> expectedBoundingBoxes = new LinkedList<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(0, 0), new Node(1,1)));
        expectedBoundingBoxes.add(new BoundingBox(new Node(4, 1), new Node(9,3)));
        expectedBoundingBoxes.add(new BoundingBox(new Node(8, 0), new Node(11, 2)));

        assertEquals(expectedBoundingBoxes, boundingBoxes);
    }

    @Test
    public void testFindNonOverlappingBoundingBoxesSimple() {
        boolean[][] array = TestResources.getSimpleArray();
        List<BoundingBox> boundingBoxes = BoundingBoxService.findBoundingBoxes(array);
        Set<BoundingBox> nonOverlapping =
            BoundingBoxService.findNonOverlappingBoundingBoxes(boundingBoxes);

        Set<BoundingBox> expectedBoundingBoxes = new HashSet<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(1, 1), new Node(2,2)));

        assertEquals(expectedBoundingBoxes, nonOverlapping);
    }

    @Test
    public void testFindNonOverlappingBoundingBoxesGroups() {
        boolean[][] array = TestResources.getGroupsArray();
        List<BoundingBox> boundingBoxes = BoundingBoxService.findBoundingBoxes(array);
        Set<BoundingBox> nonOverlapping =
            BoundingBoxService.findNonOverlappingBoundingBoxes(boundingBoxes);

        Set<BoundingBox> expectedBoundingBoxes = new HashSet<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(0, 0), new Node(1,1)));

        assertEquals(expectedBoundingBoxes, nonOverlapping);
    }

    @Test
    public void testFindLargestBoundingBoxesEmpty() {
        Set<BoundingBox> emptyList = new HashSet<>();
        assertEquals(emptyList, BoundingBoxService.findLargestBoundingBoxes(emptyList));
    }

    @Test
    public void testFindLargestBoundingBoxesOneBigOneSmall() {
        BoundingBox bigBox = new BoundingBox(new Node(0, 0), new Node(10,10));
        BoundingBox smallBox = new BoundingBox(new Node(0, 0), new Node(1,1));

        Set<BoundingBox> expectedBoundingBoxes = new HashSet<>();
        expectedBoundingBoxes.add(bigBox);

        Set<BoundingBox> allBoxes = new HashSet<>();
        allBoxes.add(bigBox);
        allBoxes.add(smallBox);

        assertEquals(expectedBoundingBoxes, BoundingBoxService.findLargestBoundingBoxes(allBoxes));
    }

    @Test
    public void testFindLargestBoundingBoxesTwoBig() {
        BoundingBox bigBox = new BoundingBox(new Node(0, 0), new Node(10,10));
        BoundingBox equallyBigBox = new BoundingBox(new Node(10, 10), new Node(20,20));

        Set<BoundingBox> allBoxes = new HashSet<>();
        allBoxes.add(bigBox);
        allBoxes.add(equallyBigBox);

        assertEquals(allBoxes, BoundingBoxService.findLargestBoundingBoxes(allBoxes));
    }

    @Test
    public void findBiggestNonOverlappingBoundingBoxSimple() {
        boolean[][] array = TestResources.getSimpleArray();

        Set<BoundingBox> expectedBoundingBoxes = new HashSet<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(1, 1), new Node(2,2)));

        assertEquals(expectedBoundingBoxes, BoundingBoxService.findBiggestNonOverlappingBoundingBox(array));
    }

    @Test
    public void findBiggestNonOverlappingBoundingBoxGroups() {
        boolean[][] array = TestResources.getGroupsArray();

        Set<BoundingBox> expectedBoundingBoxes = new HashSet<>();
        expectedBoundingBoxes.add(new BoundingBox(new Node(0, 0), new Node(1,1)));

        assertEquals(expectedBoundingBoxes, BoundingBoxService.findBiggestNonOverlappingBoundingBox(array));
    }
}