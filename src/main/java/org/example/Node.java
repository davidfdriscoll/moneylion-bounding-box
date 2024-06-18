package org.example;

public record Node(int x, int y) {
    @Override
    public String toString() {
        return "(" + (x + 1) + "," + (y + 1) + ")";
    }
}
