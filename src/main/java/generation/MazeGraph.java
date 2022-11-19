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
     */
    public void addEdge(int first, int second) {
        // Edges are a set (No duplicates)
        if (containsEdge(first, second)) {
            return;
        }
        addDirectedEdge(first, second);
        addDirectedEdge(second, first);
        edgeCount++;
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

    /**
     * Getter method for the vertices in the graph
     * @return set of cells that make up the graph
     */
    public Map<Integer, Cell> cellMap() {
        Map<Integer, Cell> cells = new HashMap<>();

        // Look at all vertices
        for (int key : adjacencyLists.keySet()) {
            // Add at each edge in the linked list
            Node current = adjacencyLists.get(key);

            Cell newCell = new Cell();
            while (current != null) {
                int wallReference = key - current.vertex;
                if (wallReference < 0) {
                    if (wallReference == -1) {
                        newCell.setDoor(Cell.EAST); // West
                    }
                    else {
                        newCell.setDoor(Cell.SOUTH); // South
                    }
                }
                else {
                    if (wallReference == 1) {
                        newCell.setDoor(Cell.WEST); // East
                    }
                    else {
                        newCell.setDoor(Cell.NORTH); // North
                    }
                }
                current = current.next;
            }
            // Track key as added
            cells.put(key, newCell);
        }
        return cells;
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
    private static class Node {
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
