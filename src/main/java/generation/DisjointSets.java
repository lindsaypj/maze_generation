package generation;

import java.util.Arrays;

/**
 * Implements the union-find algorithm for tracking and merging disjoint sets.
 *
 * @author Josh Archer
 * @version 1.0
 */
public class DisjointSets
{
    private int[] sets;

    /**
     * Creates a new data structure with the requested number of sets.
     *
     * @param numSets the number of sets
     */
    public DisjointSets(int numSets)
    {
        sets = new int[numSets];

        //each tree is off height 1 (-1) to generateMaze with
        Arrays.fill(sets, -1);
    }

    /**
     * Finds the representative element of the set the given element belongs to.
     *
     * @param element the element to search from
     * @return the representative of a set
     */
    public int find(int element)
    {
        if (sets[element] < 0)
        {
            return element;
        }
        return sets[element] = find(sets[element]);
    }

    /**
     * Joins together two sets given an element from each set.
     *
     * @param first the first element
     * @param second the second element
     * @return true if the two sets were joined, or false if both elements
     * are from the same set
     */
    public boolean union(int first, int second)
    {
        int firstRoot = find(first);
        int secondRoot = find(second);

        if (firstRoot != secondRoot)
        {
            if (sets[firstRoot] < sets[secondRoot])
            {
                sets[secondRoot] = firstRoot;
            }
            else if (sets[secondRoot] < sets[firstRoot])
            {
                sets[firstRoot] = secondRoot;
            }
            else if (sets[firstRoot] == sets[secondRoot])
            {
                //just pick one and increase height of tree
                sets[secondRoot] = firstRoot;
                sets[firstRoot]--;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given elements belong to the same set.
     *
     * @param first the first element
     * @param second the second element
     * @return true if both elements are from the same set
     */
    public boolean sameSet(int first, int second)
    {
        return find(first) == find(second);
    }

    @Override
    public String toString()
    {
        return "DisjointSets{ " + Arrays.toString(sets) + '}';
    }
}
