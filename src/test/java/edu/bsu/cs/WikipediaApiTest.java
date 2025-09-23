package edu.bsu.cs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class WikipediaApiTest {

    @Test
    public void testGetJSONConnection() throws IOException, URISyntaxException {
        WikipediaAPI api = new WikipediaAPI();
        URLConnection connection = api.getJSON("Frank Zappa");

        assertNotNull(connection, "Connection should not be null");
        assertTrue(connection.getURL().toString().contains("Frank+Zappa"));
    }

    @Test
    public void testGetJSONReturnsValidData() throws IOException, URISyntaxException {
        WikipediaAPI api = new WikipediaAPI();
        URLConnection connection = api.getJSON("Wikipedia");

        // Read JSON data directly since readJsonAsStringFrom is private
        String jsonData = new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        assertNotNull(connection, "Connection should not be null");
        assertFalse(jsonData.isEmpty(), "JSON data should not be empty");
        assertTrue(jsonData.contains("query"), "JSON should contain Wikipedia API query structure");
    }

    @Test
    public void testURLContainsRequiredParameters() throws IOException, URISyntaxException {
        WikipediaAPI api = new WikipediaAPI();
        URLConnection connection = api.getJSON("Test Article");

        String url = connection.getURL().toString();
        assertTrue(url.contains("en.wikipedia.org"));
        assertTrue(url.contains("api.php"));
        assertTrue(url.contains("action=query"));
        assertTrue(url.contains("format=json"));
        assertTrue(url.contains("prop=revisions"));
        assertTrue(url.contains("Test+Article"));
        assertTrue(url.contains("rvprop=timestamp%7Cuser"));
        assertTrue(url.contains("rvlimit=4"));
        assertTrue(url.contains("redirects"));
    }

    @Test
    public void testSpecialCharacterEncoding() throws IOException, URISyntaxException {
        WikipediaAPI api = new WikipediaAPI();
        URLConnection connection = api.getJSON("C++");

        assertTrue(connection.getURL().toString().contains("C%2B%2B"));
    }
}