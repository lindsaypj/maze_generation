package generation;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test for the DisjointSets class.
 * Tests the find(), union(), and sameSet() methods.
 *
 * @author Patrick Lindsay
 * @version 1.0
 */
class DisjointSetsTest {
    private static final int SET_SIZE = 10;

    @Test
    void testFind() {
        // Create test set
        DisjointSets set = new DisjointSets(SET_SIZE);

        // Test find on disjoint sets/ roots
        for (int i = 0; i < SET_SIZE; i++) {
            assertEquals(set.find(i), i);
        }

        // Test out of bounds
        boolean pass = true;
        try {
            set.find(-1); // Lower bounds
            pass = false;
        } catch (Exception ignored) {}
        finally {
            assertTrue(pass);
        }
        boolean pass2 = true;
        try {
            set.find(SET_SIZE + 1); // Upper bounds
            pass2 = false;
        } catch (Exception ignored) {}
        finally {
            assertTrue(pass2);
        }

        // Test non-root
        set.union(0,1);
        set.union(0,2);
        assertTrue(set.find(2) == 0 || set.find(2) == 1);
    }

    @Test
    void testUnionSameSet() {
        // Create test set
        DisjointSets set = new DisjointSets(SET_SIZE);
        set.union(0,1); // 0-1
        set.union(0,2); // 0-1-2
        set.union(2,3); // 0-1-2-3
        set.union(4,5); // 4-5
        set.union(8,6); // 8-6
        set.union(9,6); // 8-6-9
                        // 0-1-2-3  4-5  7  6-8-9
        HashSet<Integer> zeroSet = new HashSet<>();
        zeroSet.add(0);
        zeroSet.add(1);
        zeroSet.add(2);
        zeroSet.add(3);
        HashSet<Integer> fourSet = new HashSet<>();
        fourSet.add(4);
        fourSet.add(5);
        HashSet<Integer> sixSet = new HashSet<>();
        sixSet.add(6);
        sixSet.add(8);
        sixSet.add(9);
        // Test unions
        // 0
        testSameSetTwo(set, 0, zeroSet);
        // 1
        testSameSetTwo(set, 1, zeroSet);
        // 2
        testSameSetTwo(set, 2, zeroSet);
        // 3
        testSameSetTwo(set, 3, zeroSet);
        // 4
        testSameSetTwo(set, 4, fourSet);
        // 5
        testSameSetTwo(set, 5, fourSet);
        // 6
        testSameSetTwo(set, 6, sixSet);
        // 7
        testSameSetOne(set, 7);
        // 8
        testSameSetTwo(set, 8, sixSet);
        // 9
        testSameSetTwo(set, 9, sixSet);
    }

    private void testSameSetOne(DisjointSets set, int index) {
        for (int i = 0; i < SET_SIZE; i++) {
            if (i != index) {
                assertFalse(set.sameSet(index, i));
            }
            else {
                assertTrue(set.sameSet(index, i));
            }
        }
    }

    private void testSameSetTwo(DisjointSets set, int index, HashSet<Integer> connected) {
        for (int i = 0; i < SET_SIZE; i++) {
            if (connected.contains(i)) {
                assertTrue(set.sameSet(index, i));
            }
            else {
                assertFalse(set.sameSet(index, i));
            }
        }
    }
}