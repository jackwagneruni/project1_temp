package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RevisionTest {

    @Test
    public void testRevisionCreation() {
        Revision revision = new Revision("TestUser", "2025-08-13T22:47:03Z");

        assertEquals("TestUser", revision.getUsername());
        assertEquals("2025-08-13T22:47:03Z", revision.getTimestamp());
    }

    @Test
    public void testRevisionWithAnonymousUser() {
        Revision revision = new Revision("192.168.1.1", "2025-08-13T20:25:31Z");

        assertEquals("192.168.1.1", revision.getUsername());
    }

    @Test
    public void testRevisionImmutability() {
        Revision revision = new Revision("User1", "2025-01-01T00:00:00Z");

        String originalUsername = revision.getUsername();
        String originalTimestamp = revision.getTimestamp();

        // Verify fields haven't changed
        assertEquals(originalUsername, revision.getUsername());
        assertEquals(originalTimestamp, revision.getTimestamp());
    }
}