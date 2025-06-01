#!/bin/bash

# Build script for Dijkstra's algorithm implementation

echo "Building Dijkstra's Algorithm project..."

chmod +x build.sh
# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java source files
javac -cp lib/jdom-2.0.6/jdom-2.0.6.jar -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "Build successful! Compiled classes are in bin/"
else
    echo "Build failed!"
    exit 1
fi
