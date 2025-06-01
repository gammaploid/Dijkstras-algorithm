import java.util.List;
import java.util.Map;

/**
 * Interface for a weighted directed graph
 */
public interface Graph {
    
    /**
     * Add a vertex to the graph
     * @param vertex the vertex to add
     */
    void addVertex(Vertex vertex);
    
    /**
     * Add a weighted edge from source to target
     * @param source the source vertex
     * @param target the target vertex
     * @param weight the weight of the edge
     */
    void addEdge(Vertex source, Vertex target, int weight);
    
    /**
     * Get all vertices in the graph
     * @return list of all vertices
     */
    List<Vertex> getVertices();
    
    /**
     * Get adjacent vertices with their weights
     * @param vertex the vertex to get adjacents for
     * @return map of adjacent vertices to their edge weights
     */
    Map<Vertex, Integer> getAdjacentVertices(Vertex vertex);
    
    /**
     * Check if a vertex exists in the graph
     * @param vertex the vertex to check
     * @return true if vertex exists
     */
    boolean hasVertex(Vertex vertex);
    
    /**
     * Get a vertex by its label
     * @param label the label of the vertex
     * @return the vertex with the given label, or null if not found
     */
    Vertex getVertex(String label);
    
    /**
     * Get the number of vertices in the graph
     * @return number of vertices
     */
    int getVertexCount();
}
