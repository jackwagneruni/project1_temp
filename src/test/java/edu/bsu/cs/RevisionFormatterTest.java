package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionFormatterTest {

    @Test
    public void testFormatRevisions() {
        Revision rev1 = new Revision("User1", "2025-08-13T22:47:03Z");
        Revision rev2 = new Revision("User2", "2025-08-13T20:25:31Z");
        List<Revision> revisions = List.of(rev1, rev2);

        RevisionFormatter formatter = new RevisionFormatter();
        String output = formatter.format(revisions);

        String expected = "1  2025-08-13T22:47:03Z  User1\n" +
                "2  2025-08-13T20:25:31Z  User2\n";

        assertEquals(expected, output);
    }

    @Test
    public void testFormatRedirect() {
        RevisionFormatter formatter = new RevisionFormatter();
        String output = formatter.formatRedirect("Frank Zappa");

        assertEquals("Redirected to Frank Zappa\n", output);
    }
}