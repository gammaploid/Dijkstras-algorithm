#!/bin/bash

# Test script for Dijkstra's algorithm implementationrubn

echo "=== Dijkstra's Algorithm Test Suite ==="
echo

# Compile source files to bin directory
echo "Compiling Java source files..."
javac -cp lib/jdom-2.0.6/jdom-2.0.6.jar -d bin src/*.java
echo "Compilation complete."
echo

echo "Test 1: GraphSpecExample (4 vertices)"
echo "Actual:"
echo "data/graphs/graphSpecExample.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo

  
echo "Test 2: Graph10 (10 vertices)"
echo "Actual:"
echo "data/graphs/graph10.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo

echo "Test 3: Graph20 (20 vertices)"
echo "Actual:"
echo "data/graphs/graph20.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo




echo "Test 4: Graph100A (100 vertices)"
echo "Actual:"
echo "data/graphs/graph100A.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo
echo "Test 5: Graph100B (100 vertices)"
echo "Actual:"
echo "data/graphs/graph100B.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo

echo "Test 6: Graph1000 (1000 vertices)"
echo "Actual:"
echo "data/graphs/graph1000.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo


# Unreachable vertices
echo "Test 7: Random graph with unreachable vertices, 10 vertices, 10 edges"
echo "Actual:"
echo "data/graphs/random_v10_e10_w50.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo

echo "Test 8: Random graph with unreachable vertices, 100 vertices, 100 edges"
echo "Actual:"
echo "data/graphs/random_v100_e100_w50.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo


echo "Test 9: Random graph with unreachable 100 vertices, 500 edges"
echo "Actual:"
echo "data/graphs/random_v100_e500_w50.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo


echo "Test 10: Random graph with unreachable 100 vertices, 1000 edges"
echo "Actual:"
echo "data/graphs/random_v100_e1000_w50.graphml 0" | java -cp lib/jdom-2.0.6/jdom-2.0.6.jar:bin GraphShortestPathDriver
echo


echo "=== All tests completed ==="
