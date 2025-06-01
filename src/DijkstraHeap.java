import java.util.*;

/**
 * Custom heap-based priority queue optimized for Dijkstra's algorithm
 */
public class DijkstraHeap {
    
    private List<HeapNode> heap;
    private Map<Vertex, Integer> vertexToIndex;
    
    /**
     * Node in the heap containing vertex and its distance
     */
    static class HeapNode {
        Vertex vertex;
        int distance;
        
        HeapNode(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
    
    public DijkstraHeap() {
        this.heap = new ArrayList<>();
        this.vertexToIndex = new HashMap<>();
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    public int size() {
        return heap.size();
    }
    
    /**
     * Insert a new vertex with its distance
     */
    public void insert(Vertex vertex, int distance) {
        HeapNode node = new HeapNode(vertex, distance);
        heap.add(node);
        int index = heap.size() - 1;
        vertexToIndex.put(vertex, index);
        heapifyUp(index);
    }
    
    /**
     * Extract the vertex with minimum distance
     */
    public Vertex extractMin() {
        if (heap.isEmpty()) {
            return null;
        }
        
        HeapNode minNode = heap.get(0);
        Vertex minVertex = minNode.vertex;
        
        // Move last element to root and remove last
        HeapNode lastNode = heap.get(heap.size() - 1);
        heap.set(0, lastNode);
        vertexToIndex.put(lastNode.vertex, 0);
        heap.remove(heap.size() - 1);
        vertexToIndex.remove(minVertex);
        
        if (!heap.isEmpty()) {
            heapifyDown(0);
        }
        
        return minVertex;
    }
    
    /**
     * Decrease the distance of a vertex (key decrease operation)
     */
    public void decreaseKey(Vertex vertex, int newDistance) {
        Integer index = vertexToIndex.get(vertex);
        if (index == null) {
            // Vertex not in heap, insert it
            insert(vertex, newDistance);
            return;
        }
        
        HeapNode node = heap.get(index);
        if (newDistance < node.distance) {
            node.distance = newDistance;
            heapifyUp(index);
        }
    }
    
    /**
     * Check if vertex is in the heap
     */
    public boolean contains(Vertex vertex) {
        return vertexToIndex.containsKey(vertex);
    }
    
    /**
     * Get the current distance of a vertex in the heap
     */
    public int getDistance(Vertex vertex) {
        Integer index = vertexToIndex.get(vertex);
        if (index == null) {
            return Integer.MAX_VALUE;
        }
        return heap.get(index).distance;
    }
    
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).distance >= heap.get(parentIndex).distance) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }
    
    private void heapifyDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;
            
            if (leftChild < heap.size() && 
                heap.get(leftChild).distance < heap.get(smallest).distance) {
                smallest = leftChild;
            }
            
            if (rightChild < heap.size() && 
                heap.get(rightChild).distance < heap.get(smallest).distance) {
                smallest = rightChild;
            }
            
            if (smallest == index) {
                break;
            }
            
            swap(index, smallest);
            index = smallest;
        }
    }
    
    private void swap(int i, int j) {
        HeapNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        
        // Update index mapping
        vertexToIndex.put(heap.get(i).vertex, i);
        vertexToIndex.put(heap.get(j).vertex, j);
    }
    
    /**
     * Dijkstra using custom heap implementation
     */
    public static DijkstraShortestPath.DijkstraResult dijkstraWithCustomHeap(Graph graph, Vertex source) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> predecessors = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();
        DijkstraHeap heap = new DijkstraHeap();
        
        // Initialize distances
        for (Vertex v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        heap.insert(source, 0);
        
        while (!heap.isEmpty()) {
            Vertex current = heap.extractMin();
            
            if (visited.contains(current)) {
                continue;
            }
            
            visited.add(current);
            
            // Update distances to neighbors
            Map<Vertex, Integer> neighbors = graph.getAdjacentVertices(current);
            for (Map.Entry<Vertex, Integer> entry : neighbors.entrySet()) {
                Vertex neighbor = entry.getKey();
                int weight = entry.getValue();
                
                if (!visited.contains(neighbor)) {
                    int newDistance = distances.get(current) + weight;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        predecessors.put(neighbor, current);
                        heap.decreaseKey(neighbor, newDistance);
                    }
                }
            }
        }
        
        return new DijkstraShortestPath.DijkstraResult(distances, predecessors, source);
    }
}
