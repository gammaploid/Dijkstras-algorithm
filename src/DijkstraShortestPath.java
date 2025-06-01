import java.util.*;

/**
 * Implementation of Dijkstra's shortest path algorithm
 */
public class DijkstraShortestPath {
    
    public static class DijkstraResult {
        private Map<Vertex, Integer> distances;
        private Map<Vertex, Vertex> predecessors;
        private Vertex sourceVertex;
        
        public DijkstraResult(Map<Vertex, Integer> distances, Map<Vertex, Vertex> predecessors, Vertex sourceVertex) {
            this.distances = distances;
            this.predecessors = predecessors;
            this.sourceVertex = sourceVertex;
        }
        
        /**
         * Returns the shortest distance from the source vertex to the given vertex.
         * If no path was found, returns Integer.MAX_VALUE.
         *
         * @param vertex the target vertex
         * @return the shortest distance from the source vertex to the target vertex
         */
        public int getDistance(Vertex vertex) {
            return distances.getOrDefault(vertex, Integer.MAX_VALUE);
        }
        
        /**
         * Returns the shortest path from the source vertex to the given vertex.
         * If no path was found, returns an empty list.
         *
         * @param target the target vertex
         * @return the shortest path from the source vertex to the target vertex
         */
        public List<Vertex> getPath(Vertex target) {
            List<Vertex> path = new ArrayList<>();
            Vertex current = target;
            
            if (getDistance(target) == Integer.MAX_VALUE) {
                return path;
            }
            
            while (current != null) {
                path.add(current);
                current = predecessors.get(current);
            }
            // Reverse the path to get it from source to target
            Collections.reverse(path);
            return path;
        }
        
/**
 * Prints the shortest path and distance from the source vertex to each vertex in the graph.
 * If no path exists to a vertex, prints "NO PATH".
 *
 * @param graph the graph containing vertices
 */

        public void printResults(Graph graph) {
            List<Vertex> vertices = graph.getVertices();
            
            for (Vertex vertex : vertices) {
                List<Vertex> path = getPath(vertex);
                int distance = getDistance(vertex);
                
                System.out.print("shortest path to " + vertex.getLabel() + ": ");
                
                if (path.isEmpty()) {
                    System.out.println("NO PATH");
                } else {
                    for (int i = 0; i < path.size(); i++) {
                       if (i > 0) System.out.print(" ");
                        System.out.print(path.get(i).getLabel());
                    }
                    
                    System.out.println(": cost = " + distance);
                }
            }
        }
    }
    
    /**
     * Dijkstra's algorithm for finding shortest paths from a single source vertex
     * in a weighted directed graph. This implementation uses a priority queue
     * to find the next vertex to visit.
     * 
     * @param graph the graph containing vertices
     * @param source the source vertex
     * @return a DijkstraResult, containing a map of distances and a map of predecessors
     */
    public static DijkstraResult dijkstraWithPriorityQueue(Graph graph, Vertex source) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> predecessors = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        // Priority queue to select the vertex with the smallest distance
        PriorityQueue<VertexDistance> pq  = new PriorityQueue<>(Comparator.comparingInt(vd -> vd.distance)) ;
        

        // Initialize distances loop through all vertices
        for (Vertex v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        pq.offer(new VertexDistance(source, 0));
        
        // while there are vertices to process loop through the priority queue
        while (!pq.isEmpty()) {
            VertexDistance current = pq.poll();
            
            if (visited.contains(current.vertex)) {
                continue;
            }
            
            visited.add(current.vertex);
            // Update distances to neighbors 
            Map<Vertex, Integer> neighbors = graph.getAdjacentVertices(current.vertex);
            
            // loop through neighbors of current vertex
            // and update distances if a shorter path is found
            for (Map.Entry<Vertex, Integer> entry : neighbors.entrySet()) {
                Vertex neighbor = entry.getKey();
                int weight = entry.getValue();
                
                if (!visited.contains(neighbor)) {

                    int newDistance = distances.get(current.vertex) +  weight;
                    if (newDistance <distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        predecessors.put(neighbor, current.vertex);
                        //offer is a priority queue operation that adds an element
                        pq.offer(new VertexDistance(neighbor, newDistance));
                    }
                }
            }
        }
        // Return the result containing distances and predecessors
        return new DijkstraResult(distances, predecessors, source);
    }
    
    /**
     * Dijkstra using ArrayList 
     */
    public static DijkstraResult dijkstraWithArrayList(Graph graph, Vertex source) {

        Map<Vertex, Integer> distances = new HashMap<>(); // distances from source to each vertex
        Map<Vertex, Vertex> predecessors = new HashMap<>(); // predecessors for path reconstruction
        Set<Vertex> visited = new HashSet<>(); // set of visited vertices
        List<Vertex> unvisited = new ArrayList<>(); // list of unvisited vertices
        
        // init distances
        for (Vertex v : graph.getVertices()) {
            // predecessors.put(v, null); 
            distances.put(v, Integer.MAX_VALUE);
            unvisited.add(v);
        }
        distances.put(source, 0); // distance to source is 0
        
        while (!unvisited.isEmpty() ) {

            // find vertex with minimum distance (linear search)
            Vertex current = null;
            int minDistance = Integer.MAX_VALUE; // initialize to max value i.e. infinity
            // iterate through unvisited vertices to find the one with minimum distance
            // this is O(n) operation
            
            // loop through unvisited vertices
            // and find the one with minimum distance
            for (Vertex v : unvisited) {
                if (distances.get(v) <  minDistance) {
                    minDistance = distances.get(v);
                    current = v;
                }
            }
            
            if (current == null || minDistance == Integer.MAX_VALUE) {
                break; // no more reachable vertices
             }
            // Mark current vertex as visited i.e remove it from unvisited
            // and add it to visited set
            unvisited.remove(current);
            visited.add(current);
            
            // Update distances to neighbors
            Map<Vertex, Integer> neighbors = graph.getAdjacentVertices(current);
            // loop through neighbours of current vertex
            for (Map.Entry<Vertex, Integer> entry : neighbors.entrySet()) {
                Vertex neighbor =  entry.getKey();
                int weight = entry.getValue();
                
                if (!visited.contains(neighbor)) {
                    int newDistance = distances.get(current) + weight;
                    // if new distance is less than current distance, update it
                    // and set predecessor
                    if (newDistance < distances.get(neighbor)) {
                         distances.put( neighbor, newDistance);
                        predecessors.put(neighbor, current);
                    }
                }
            }
        }
        // Return the result containing distances and predecessors
        return new DijkstraResult(distances, predecessors, source);
 }

    static class VertexDistance {
        Vertex vertex;
        int distance;
// Constructor for VertexDistance        
        VertexDistance(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
}
