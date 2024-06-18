package org.example;

import java.util.*;

public class BoundingBoxService {
    public static List<BoundingBox> findBoundingBoxes(boolean[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        List<BoundingBox> boundingBoxes = new LinkedList<>();

        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row < numRows; row++) {
                if (grid[row][col]) {
                    Node node = new Node(col, row);
                    boundingBoxes.add(traverseGrid(grid, node));
                }
            }
        }
        return boundingBoxes;
    }

    private static BoundingBox traverseGrid(boolean[][] grid, Node startNode) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        Set<Node> directions = new HashSet<>();
        directions.add(new Node(1, 0));
        directions.add(new Node(0, 1));
        directions.add(new Node(-1, 0));
        directions.add(new Node(0, -1));

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
