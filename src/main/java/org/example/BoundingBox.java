package org.example;

public record BoundingBox(Node topLeft, Node bottomRight) {
    public int area() {
        return (bottomRight.x() - topLeft.x()) * (bottomRight.y() - topLeft().y());
    }
}
