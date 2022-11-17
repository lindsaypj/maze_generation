package gui;

import generation.AlgorithmType;
import generation.DisjointSets;
import generation.MazeGraph;

/**
 * Generates a random maze using disjoint sets,
 * Represents the maze as a graph,
 * Solves the maze using various traversal algorithms
 *
 * @author Patrick Lindsay
 * @version 1.0
 */
public class MazeUICompleted extends MazeUI
{
    private DisjointSets sets;
    private MazeGraph graph;

    @Override
    public void runAlgorithm(AlgorithmType type)
    {
        switch(type) {
            case GENERATE_MAZE:
                generateMaze();
                break;
            case DFS:
                dfs();
                break;
            case BFS:
                bfs();
        }

    }

    /* Generates a random maze using the DisjointSets class, which in turn implements the
     * unionByHeight-find algorithm. A graph is then used to represent the cells in the maze.
     * The graph is then used to draw the maze.
     */
    private void generateMaze() {
        // Initialize Maze data structures
        int cellCount = getRows() * getCols();
        sets = new DisjointSets(cellCount);
        graph = new MazeGraph(cellCount);


        // Add edges to the graph randomly to form maze
    }

    private void dfs() {

    }

    private void bfs() {

    }

    @Override
    public String toString() {
        return "MazeUICompleted{}";
    }
}
