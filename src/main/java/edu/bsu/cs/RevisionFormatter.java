package edu.bsu.cs;

import java.util.List;

public class RevisionFormatter {

    public String format(List<Revision> revisions) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < revisions.size(); i++) {
            Revision revision = revisions.get(i);
            int lineNumber = i + 1;

            output.append(lineNumber)
                    .append("  ")
                    .append(revision.getTimestamp())
                    .append("  ")
                    .append(revision.getUsername())
                    .append("\n");
        }

        return output.toString();
    }

    public String formatRedirect(String redirectTitle) {
        return "Redirected to " + redirectTitle + "\n";
    }
}