# Dijkstra's Algorithm Implementation

This project provides Java implementations of Dijkstra's shortest path algorithm. It includes versions using a standard Priority Queue, an ArrayList, and a custom-built heap (DijkstraHeap).

## Project Structure

- `src/`: Contains all Java source code including:
    - `DijkstraShortestPath.java`: Implements Dijkstra's using `java.util.PriorityQueue` and `java.util.ArrayList`.
    - `DijkstraHeap.java`: A custom min-heap implementation and Dijkstra's algorithm using this heap.
    - `Graph.java`, `Vertex.java`, `MyGraph.java`: Core graph data structures.
    - `GraphBuilder.java`: Utility to build graphs from GraphML files.
    - `GraphShortestPathDriver.java`: Main driver class to run the algorithms.
- `lib/`: Contains the JDOM library for parsing GraphML files.
- `data/`: Contains sample graph data in GraphML format and corresponding output files.
- `bin/`: Compiled Java class files (created by the build script).
- `build.sh`: Script to compile the Java source code.
- `run_tests.sh`: Script to run tests (details to be added if available).
- `Report.pdf`: Project report.

## Usage

1.  **Build the project:**
    Open a terminal in the project root directory and run:
    ```bash
    ./build.sh
    ```
    This will compile the Java files and place them in the `bin/` directory.

2.  **Run the algorithm:**
    Execute the `GraphShortestPathDriver` class. (You might need to refer to `run_tests.sh` or provide specific command examples for running with different graphs and source vertices).

    Example (conceptual, actual command might vary):
    ```bash
    java -cp bin:lib/jdom-2.0.6/jdom-2.0.6.jar GraphShortestPathDriver data/graphs/graphSpecExample.graphml A PQ
    ```
    This (conceptual) command would run Dijkstra's algorithm using the Priority Queue (PQ) implementation on `graphSpecExample.graphml` starting from vertex `A`.

## Implementations

The project offers three ways to compute shortest paths:
1.  **Priority Queue:** Uses `java.util.PriorityQueue`.
2.  **ArrayList:** A basic implementation using an `java.util.ArrayList` to store unvisited nodes (less efficient).
3.  **Custom Heap:** Uses `DijkstraHeap.java`, a custom-built binary heap optimized for Dijkstra's.
