package gui;

import generation.AlgorithmType;
import generation.Cell;
import generation.DisjointSets;
import generation.MazeGraph;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
    // Maze Data
    private int cellCount;
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
        cellCount = getRows() * getCols();
        DisjointSets sets = new DisjointSets(cellCount);
        graph = new MazeGraph(cellCount);

        // Add edges to the graph randomly to form maze
        Random random = ThreadLocalRandom.current(); // Faster than Random (not thread-safe)
        while(graph.getEdgeCount() < cellCount -1) {
            // Select a random cell
            int nextCell = random.nextInt(cellCount);

            // Get random neighbor (cycle through them
            int neighbor = getNeighbor(nextCell, random.nextInt(4));

            // Check if neighbor index is valid and if they are in the same set
            if (neighbor != -1 && !sets.sameSet(nextCell, neighbor)) {
                // Union the sets
                sets.union(nextCell, neighbor);
                // Store the edge in the graph
                graph.addEdge(nextCell, neighbor);
            }
        }
        // Draw the maze
        drawMaze();
    }

    private void drawMaze() {
        clearScreen();
        setStrokeColor(Color.BLACK);
        // Draw border to match cell wall widths
        drawBorder();

        // Draw Cells
        setStrokeWidth(100/getCols() + 1);
        Map<Integer, Cell> cellMap = graph.cellMap();

        // Remove entrance and exit walls
        cellMap.get(0).setDoor(0);
        cellMap.get(cellCount-1).setDoor(2);

        for (int key : cellMap.keySet()) {
            drawCell(key, cellMap.get(key).getDoors());
        }
    }

    private void drawBorder() {
        setStrokeWidth(2 * (100/getCols() + 1));
        // North
        for (int i = 1; i < getCols(); i++) {
            drawCell(i, new boolean[]{true, false, false, false});
        }
        // East
        for (int i = getCols()-1; i < cellCount; i += getCols()) {
            drawCell(i, new boolean[]{false, true, false, false});
        }
        // South
        for (int i = getRows()*getCols() - getCols(); i < cellCount-1; i++) {
            drawCell(i, new boolean[]{false, false, true, false});
        }
        // West
        for (int i = 0; i < cellCount; i += getCols()) {
            drawCell(i, new boolean[]{false, false, false, true});
        }
    }

    // Method to calculate the neighbor of the cell in a given direction
    // Returns -1 if neighbor is invalid or out of bounds
    private int getNeighbor(int cell, int wall) {
        switch(wall) {
            case 0: // NORTH
                int northNeighbor = cell - getCols();
                return northNeighbor < 0 ? -1 : northNeighbor;
            case 1: // EAST
                // If (cell + 1) % getCols() == 0, then cell is at the end of a row
                return (cell + 1) % getCols() == 0 ? -1 : cell + 1;
            case 2: // SOUTH
                int southNeighbor = cell + getCols();
                return southNeighbor >= cellCount ? -1 : southNeighbor;
            case 3: // WEST
                // If cell % getCols() == 0, then cell is at the start of a row
                return cell % getCols() == 0 ? -1 : cell - 1;
            default:
                return -1;
        }
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
