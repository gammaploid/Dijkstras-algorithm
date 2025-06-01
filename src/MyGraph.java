import java.util.*;

/**
 * Weighted directed graph implementation using adjacency lists
 */
public class MyGraph implements Graph {
    
    private Map<String, Vertex> vertices;
    private Map<Vertex, Map<Vertex, Integer>> adjacencyList;

    
    public MyGraph() {
        this.vertices = new TreeMap<>();
        this.adjacencyList = new HashMap<>();
    }
    
    /**
     * Adds a vertex to the graph if it doesn't already exist.
     * <p>
     * If the vertex is already known, this method does nothing.
     * <p>
     * Time complexity: O(1)
     * @param vertex the vertex to add
     */
    @Override
    public void addVertex(Vertex vertex) {
        // if (vertex == null) {
        //     throw new IllegalArgumentException("Vertex cannot be null");
        // }
        if (!vertices.containsKey(vertex.getLabel())) {
            vertices.put(vertex.getLabel(), vertex);
            adjacencyList.put(vertex, new HashMap<>());
        }
    }
    
/**
 * Adds a weighted edge from the source vertex to the target vertex.
 * If the vertices are not already present in the graph, they will be added.
 * The edge weight is updated if an edge already exists between the given vertices.
 *
 * @param source the source vertex
 * @param target the target vertex
 * @param weight the weight of the edge
 */

    @Override
    public void addEdge(Vertex source, Vertex target, int weight) {
        addVertex(source);
        addVertex(target);
        // if (!adjacencyList.containsKey(source)) {
        //     adjacencyList.put(source, new HashMap<>());
        // }
        adjacencyList.get(source).put(target, weight);
    }
    
    /**
     * Returns a list of all vertices in the graph, sorted by label.
     * @return a list of all vertices in the graph
     */
    @Override
    public List<Vertex> getVertices() {
        List<Vertex> vertexList = new ArrayList<>(vertices.values());
        Collections.sort(vertexList);
    
        return vertexList;
    }
    
    /**
     * Returns a map of adjacent vertices and the weights of the edges
     * between the given vertex and its neighbours.
     * If the given vertex is not in the graph, an empty map is returned.
     * @param vertex the vertex for which to return the adjacent vertices
     * @return a map of adjacent vertices and weights
     */
    @Override
    public Map<Vertex, Integer> getAdjacentVertices(Vertex vertex) {
        return adjacencyList.getOrDefault(vertex,  new HashMap<>());
    }
    
    /**
     * Does the graph contain a vertex with the given label?
     * @param vertex the vertex to search for
     * @return true if the graph contains the vertex, false otherwise
     */
    @Override
    public boolean hasVertex(Vertex vertex) {
        return vertices.containsKey(vertex.getLabel());
    }
    

/**
 * Retrieve the vertex associated with the given label.
 * 
 * @param label the label of the vertex to retrieve
 * @return the vertex with the specified label, or null if no such vertex exists
 */

    @Override
    public Vertex getVertex(String label) {
        return vertices.get(label);
    }
    

    /**
     * Returns the number of vertices in the graph.
     * @return the number of vertices in the graph
     */
    @Override
    public int getVertexCount() {
        return vertices.size();
    }
    
    /**
     * Returns a string representation of the graph, with each vertex
     * listed on a separate line, followed by its adjacent vertices and
     * the weights of the edges between them.
     * 
     * @return a string representation of the graph
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        
        sb.append("Graph with  ").append(vertices.size()).append(" vertices:\n");
        
        for (Vertex v : getVertices()) {
            sb.append(v.getLabel()).append(" -> ");
            Map<Vertex, Integer> adj = getAdjacentVertices(v);
            for (Map.Entry<Vertex, Integer> entry : adj.entrySet()) {
                sb.append(entry.getKey().getLabel()).append("(").append(entry.getValue()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
