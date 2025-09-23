package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionParserTest {

    @Test
    public void testParseMultipleRevisions() {
        String jsonData = "{\"query\":{\"pages\":{\"10672\":{\"revisions\":[" +
                "{\"user\":\"Ernsanchez00\",\"timestamp\":\"2025-08-13T22:47:03Z\"}," +
                "{\"user\":\"ArguaBILL\",\"timestamp\":\"2025-08-13T20:25:31Z\"}," +
                "{\"user\":\"Acroterion\",\"timestamp\":\"2025-08-11T02:25:16Z\"}" +
                "]}}}}";

        InputStream stream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));
        RevisionParser parser = new RevisionParser();

        List<Revision> revisions = parser.parse(stream);

        assertEquals(3, revisions.size());
        assertEquals("Ernsanchez00", revisions.get(0).getUsername());
        assertEquals("2025-08-13T22:47:03Z", revisions.get(0).getTimestamp());
        assertEquals("ArguaBILL", revisions.get(1).getUsername());
        assertEquals("Acroterion", revisions.get(2).getUsername());
    }

    @Test
    public void testParseSingleRevision() {
        String jsonData = "{\"query\":{\"pages\":{\"10672\":{\"revisions\":[" +
                "{\"user\":\"SingleUser\",\"timestamp\":\"2025-08-13T22:47:03Z\"}" +
                "]}}}}";

        InputStream stream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));
        RevisionParser parser = new RevisionParser();

        List<Revision> revisions = parser.parse(stream);

        assertEquals(1, revisions.size());
        assertEquals("SingleUser", revisions.get(0).getUsername());
    }

    @Test
    public void testParseEmptyRevisions() {
        String jsonData = "{\"query\":{\"pages\":{\"10672\":{\"revisions\":[]}}}}";

        InputStream stream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));
        RevisionParser parser = new RevisionParser();

        List<Revision> revisions = parser.parse(stream);

        assertEquals(0, revisions.size());
    }

    @Test
    public void testDetectRedirect() {
        String jsonData = "{\"query\":{\"redirects\":[{\"from\":\"Zappa\",\"to\":\"Frank Zappa\"}]}}";

        InputStream stream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));
        RevisionParser parser = new RevisionParser();

        String redirectTitle = parser.getRedirectTitle(stream);

        assertEquals("Frank Zappa", redirectTitle);
    }

    @Test
    public void testNoRedirect() {
        String jsonData = "{\"query\":{\"pages\":{\"10672\":{\"revisions\":[]}}}}";

        InputStream stream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));
        RevisionParser parser = new RevisionParser();

        String redirectTitle = parser.getRedirectTitle(stream);

        assertNull(redirectTitle);
    }

    @Test
    public void testParseAnonymousUser() {
        String jsonData = "{\"query\":{\"pages\":{\"10672\":{\"revisions\":[" +
                "{\"user\":\"192.168.1.1\",\"timestamp\":\"2025-08-13T22:47:03Z\"}" +
                "]}}}}";

        InputStream stream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));
        RevisionParser parser = new RevisionParser();

        List<Revision> revisions = parser.parse(stream);

        assertEquals(1, revisions.size());
        assertEquals("192.168.1.1", revisions.get(0).getUsername());
    }

    @Test
    public void testParseInvalidJson() {
        String jsonData = "invalid json {{{";

        InputStream stream = new ByteArrayInputStream(jsonData.getBytes(StandardCharsets.UTF_8));
        RevisionParser parser = new RevisionParser();

        List<Revision> revisions = parser.parse(stream);

        assertEquals(0, revisions.size()); // Should return empty list on error
    }
}