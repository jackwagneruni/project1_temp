package edu.bsu.cs;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements Sort {

    @Override
    public List<Revision> sort(List<Revision> revisions) {
        if (revisions == null || revisions.isEmpty()) {
            return new ArrayList<>();
        }

        List<Revision> sorted = new ArrayList<>(revisions);
        int n = sorted.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compareTimestamps(sorted.get(j), sorted.get(j + 1)) < 0) {
                    // Swap to get reverse chronological order (newest first)
                    Revision temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }

        return sorted;
    }

    private int compareTimestamps(Revision r1, Revision r2) {
        return r1.getTimestamp().compareTo(r2.getTimestamp());
    }
}