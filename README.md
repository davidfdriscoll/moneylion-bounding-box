# Moneylion Java Take Home Challenge
## Bounding Box

A description of the takehome challenge can be found at [MoneyLion Java Take Home Challenge.md](MoneyLion%20Java%20Take%20Home%20Challenge.md)

## To run

```
cd <repo directory>
mvn package
java -jar target/moneylion-bounding-box-1.0-SNAPSHOT-jar-with-dependencies.jar < src/test/resources/simple.txt
```

Feel free to swap out simple.txt for any grid, or run interactively without piping in an input file.

## Overview

This project's approach to the takehome challenge breaks the problem into three components:

* IOReader: IOReader is a simple class that has a single method `parseFromInputStream` that accepts an InputStream, reads it until it encounters an empty line, and then returns a 2D boolean array of the resulting grid. Per the takehome challenge, IOReader does no input validation to confirm the grid matches the spec.
* BoundingBoxService: BoundingBoxService has a major method `findBiggestNonOverlappingBoundingBox` that accepts a 2D boolean grid and returns a set of the largest nonoverlapping bounding boxes.
* Main: Main joins together the IOReader & BoundingBoxService and prints the resulting bounding boxes to standard output.

## BoundingBoxService

BoundingBoxService contains the core logic. The data passes through three major steps:

1. `findBoundingBoxes`: The grid is traversed; whenever an asterisk is detected, the method kicks off a BFS of the asterisk island and computes the resulting boundary box (a BoundingBox record consisting of two Node records). Compare https://leetcode.com/problems/number-of-islands/description/.
2. `findNonOverlappingBoundingBoxes`: To remove overlapping boundary boxes, the method uses a 'sweep line' algorithm (with Event record): progressing from left to right, it maintains a list of active y-axis intervals (using Guava's RangeMap as the segment tree implementation) and removes boundary boxes if any overlaps are detected. See an overview of the algorithm here: https://www.coursera.org/lecture/algorithms-part1/rectangle-intersection-mNiwq.
3. `findLargestBoundingBoxes`: from the resulting set of bounding boxes, the method determines a list of the largest bounding boxes (permitting ties), if any.

## Testing

The project contains unit tests and integration tests.

* Unit tests for both main classes can be found at IOReaderTest and BoundingBoxServiceTest
* End-to-end integration tests (omitting the final string output) can be found in IntegrationTests. I've added 15 grids for various conditions in files in test/resources; IntegrationTests runs through each of those files, parses them, and computes the minimum boundary box.

## Development

I've used Github issues to mark steps in the development process and created PRs to implement each issue separately. The commit history is a little noisier than I would like because I didn't realize that Github repos don't have "Squash and merge" on by default; the last few commits are cleaner and are closer to my preferred trunk-based development approach.