package org.example;

public record Event(int x, int y1, int y2, boolean isStart, BoundingBox box) implements Comparable<Event> {
    @Override
    public int compareTo(Event other) {
        if (this.x != other.x) {
            return this.x - other.x;
        }
        return this.isStart == other.isStart ? 0 : (this.isStart ? -1 : 1);
    }
}
