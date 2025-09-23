package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {

    @Test
    public void testSortReverseChronological() {
        Revision older = new Revision("User1", "2025-08-13T20:25:31Z");
        Revision newer = new Revision("User2", "2025-08-13T22:47:03Z");
        List<Revision> revisions = List.of(older, newer);

        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(revisions);

        assertEquals("User2", sorted.get(0).getUsername());
        assertEquals("User1", sorted.get(1).getUsername());
        assertEquals("2025-08-13T22:47:03Z", sorted.get(0).getTimestamp());
    }

    @Test
    public void testSortAlreadySorted() {
        Revision newest = new Revision("User1", "2025-08-13T22:47:03Z");
        Revision older = new Revision("User2", "2025-08-13T20:25:31Z");
        List<Revision> revisions = List.of(newest, older);

        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(revisions);

        assertEquals("User1", sorted.get(0).getUsername());
        assertEquals("User2", sorted.get(1).getUsername());
    }

    @Test
    public void testSortMultipleRevisions() {
        Revision rev1 = new Revision("User1", "2025-08-11T02:25:16Z");
        Revision rev2 = new Revision("User2", "2025-08-13T22:47:03Z");
        Revision rev3 = new Revision("User3", "2025-08-13T20:25:31Z");
        List<Revision> revisions = List.of(rev1, rev2, rev3);

        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(revisions);

        assertEquals("User2", sorted.get(0).getUsername()); // Newest
        assertEquals("User3", sorted.get(1).getUsername()); // Middle
        assertEquals("User1", sorted.get(2).getUsername()); // Oldest
    }

    @Test
    public void testSortEmptyList() {
        List<Revision> revisions = new ArrayList<>();

        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(revisions);

        assertEquals(0, sorted.size());
    }

    @Test
    public void testSortSingleRevision() {
        Revision rev = new Revision("User1", "2025-08-13T22:47:03Z");
        List<Revision> revisions = List.of(rev);

        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(revisions);

        assertEquals(1, sorted.size());
        assertEquals("User1", sorted.get(0).getUsername());
    }

    @Test
    public void testSortNullList() {
        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(null);

        assertNotNull(sorted);
        assertEquals(0, sorted.size());
    }

    @Test
    public void testSortPreservesOriginalList() {
        Revision older = new Revision("User1", "2025-08-13T20:25:31Z");
        Revision newer = new Revision("User2", "2025-08-13T22:47:03Z");
        List<Revision> original = new ArrayList<>(List.of(older, newer));

        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(original);

        // Original list should still have same order
        assertEquals("User1", original.get(0).getUsername());
        assertEquals("User2", original.get(1).getUsername());

        // Sorted list should be reversed
        assertEquals("User2", sorted.get(0).getUsername());
        assertEquals("User1", sorted.get(1).getUsername());
    }

    @Test
    public void testSortFifteenRevisions() {
        List<Revision> revisions = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            String timestamp = String.format("2025-08-%02dT22:47:03Z", i + 1);
            revisions.add(new Revision("User" + i, timestamp));
        }

        BubbleSort sorter = new BubbleSort();
        List<Revision> sorted = sorter.sort(revisions);

        assertEquals(15, sorted.size());
        assertEquals("2025-08-15T22:47:03Z", sorted.get(0).getTimestamp()); // Most recent
        assertEquals("2025-08-01T22:47:03Z", sorted.get(14).getTimestamp()); // Oldest
    }
}