package generation;

/**
 * Container class to store the walls of a cell in the maze
 * @author Patrick Lindsay
 * @version 1.0
 */
public class Cell {
    private boolean[] doors = {true, true, true, true};

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    /**
     * Getter method for source vertex
     * @param door int representing the wall to set to door
     */
    public void setDoor(int door) {
        doors[door] = false;
    }

    /**
     * Getter method for the destination vertex
     * @return second vertex in the edge
     */
    public boolean[] getDoors() {
        return doors;
    }

    @Override
    public String toString() {
        return "Cell";
    }
}
