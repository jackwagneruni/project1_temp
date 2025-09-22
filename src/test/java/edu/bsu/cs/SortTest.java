package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

    @Test
    public void testSortInterface() {
        Sort sorter = new BubbleSort();

        Revision older = new Revision("User1", "2025-08-13T20:25:31Z");
        Revision newer = new Revision("User2", "2025-08-13T22:47:03Z");
        List<Revision> revisions = List.of(older, newer);

        List<Revision> sorted = sorter.sort(revisions);

        assertNotNull(sorted);
        assertEquals(2, sorted.size());
    }
}