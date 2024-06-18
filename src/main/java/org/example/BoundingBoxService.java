package org.example;

import java.util.*;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

public class BoundingBoxService {
    public static Set<BoundingBox> findNonOverlappingBoundingBoxes(List<BoundingBox> boxes) {
        List<Event> events = new LinkedList<>();

        for (BoundingBox box: boxes) {
            int x1 = box.topLeft().x();
            int x2 = box.bottomRight().x();
            int y1 = box.topLeft().y();
            int y2 = box.bottomRight().y();
            events.add(new Event(x1, y1, y2, true, box));
            events.add(new Event(x2, y1, y2, false, box));
        }

        Collections.sort(events);

        Set<BoundingBox> nonOverlappingBoundingBoxes = new HashSet<>();
        RangeMap<Integer, BoundingBox> intervals
                = TreeRangeMap.create();

        for (Event event: events) {
            Range<Integer> range = Range.closed(event.y1(), event.y2());
            if (event.isStart()) {
                RangeMap<Integer, BoundingBox> overlaps = intervals.subRangeMap(range);
                Collection<BoundingBox> overlappingBoxes = overlaps.asMapOfRanges().values();
                if (overlappingBoxes.isEmpty()) {
                    nonOverlappingBoundingBoxes.add(event.box());
                } else {
                    nonOverlappingBoundingBoxes.removeAll(overlappingBoxes);
                }
                intervals.put(range, event.box());
            } else {
                intervals.remove(range);
            }
        }

        return nonOverlappingBoundingBoxes;
    }

    public static List<BoundingBox> findBoundingBoxes(boolean[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        List<BoundingBox> boundingBoxes = new LinkedList<>();

        Set<Node> directions = new HashSet<>();
        directions.add(new Node(1, 0));
        directions.add(new Node(0, 1));
        directions.add(new Node(-1, 0));
        directions.add(new Node(0, -1));

        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                if (grid[row][col]) {
                    Node node = new Node(col, row);
                    BoundingBox box = traverseGrid(grid, node, directions);
                    boundingBoxes.addLast(box);
                }
            }
        }
        return boundingBoxes;
    }

    private static BoundingBox traverseGrid(boolean[][] grid, Node startNode, Set<Node> directions) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        Deque<Node> queue = new LinkedList<>();
        queue.add(startNode);

        int top = startNode.y();
        int bottom = startNode.y();
        int left = startNode.x();
        int right = startNode.x();

        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            if (grid[node.y()][node.x()]) {
                grid[node.y()][node.x()] = false;
                top = Math.min(node.y(), top);
                bottom = Math.max(node.y(), bottom);
                left = Math.min(node.x(), left);
                right = Math.max(node.x(), right);
                for (Node direction: directions) {
                    int nextX = node.x() + direction.x();
                    int nextY = node.y() + direction.y();
                    if (nextX >= 0 &&
                        nextX < numCols &&
                        nextY >= 0 &&
                        nextY < numRows &&
                        grid[nextY][nextX]
                        ) {
                        queue.add(new Node(nextX, nextY));
                    }
                }
            }
        }

        Node topLeft = new Node(left, top);
        Node bottomRight = new Node(right, bottom);
        return new BoundingBox(topLeft, bottomRight);
    }
}
