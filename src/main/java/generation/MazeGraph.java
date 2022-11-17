package generation;

import java.util.HashMap;
import java.util.Map;

/**
 * This class creates a graph structure to map the cells in a maze
 * and their connections.
 *
 * Graph is undirected and unweighted, implemented using an Adjacency list
 * due to low edge density
 *
 * @author Patrick Lindsay
 * @version 1.0
 */
public class MazeGraph {
    private Map<Integer, Node> adjacencyLists = new HashMap<>();
    private int edgeCount = 0;

    /**
     * Constructor for generating a new maze with no connections.
     * @param vertices number of cells in the maze
     */
    public MazeGraph(int vertices) {
        // Add all the cells to the graph
        for (int i = 0; i < vertices; i++) {
            adjacencyLists.put(i, null);
        }
    }

    /**
     * Method to add an edge in the graph. An edge represents an open path between
     * two adjacent cells in the maze
     * @param first an index representing a cell in the maze
     * @param second another index representing a cell in the maze other than first
     * @return true if the edge was added, otherwise false
     */
    public boolean addEdge(int first, int second) {
        // Edges are a set (No duplicates)
        if (containsEdge(first, second)) {
            return false;
        }

        addDirectedEdge(first, second);
        addDirectedEdge(second, first);
        edgeCount++;

        return true;
    }

    private void addDirectedEdge(int first, int second) {
        Node oldHead = adjacencyLists.get(first);
        if (oldHead == null) {
            adjacencyLists.put(first, new Node(second));
        }
        else {
            // Put new node at the start of the LinkedList
            adjacencyLists.put(first, new Node(second, oldHead));
        }
    }

    private boolean containsVertex(int search) {
        return adjacencyLists.containsKey(search);
    }

    private boolean containsEdge(int first, int second) {
        // Check that the vertices are in the graph
        if (containsVertex(first) && containsVertex(second)) {
            // Get the adjacency list of one vertex (both work with undirected graph)
            Node current = adjacencyLists.get(first);

            // Search adjacency list for the other value
            while(current != null) {
                if (current.vertex == second) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    /**
     * Method to return the number of edges stored in the graph
     * @return number of undirected edges between any two vertices in the graph
     */
    public int getEdgeCount() {
        return edgeCount;
    }

    // Inner Classes
    private class Node {
        // Data in node
        private int vertex;

        // Next Node
        private Node next;

        public Node(int otherVertex) {
            this.vertex = otherVertex;
        }

        public Node(int otherVertex, Node next) {
            this.vertex = otherVertex;
            this.next = next;
        }

        @Override
        public String toString() { return "Node{}"; }
    }

    @Override
    public String toString() {
        return "MazeGraph{}";
    }
}
