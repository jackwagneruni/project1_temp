package edu.bsu.cs;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikipediaAPI {
    public static void main(String[] args) throws IOException, URISyntaxException {
        WikipediaAPI api = new WikipediaAPI();
        URLConnection connection = connectToWikipedia();
        String jsonData = readJsonAsStringFrom(connection);

        System.out.println(jsonData);
    }

    private static URLConnection connectToWikipedia() {
        return null;
    }

    public URLConnection getJSON(String articleName) throws IOException, URISyntaxException{
        String encodedArticleName = URLEncoder.encode(articleName, StandardCharsets.UTF_8);

        String urlString = "https://en.wikipedia.org/w/api.php" +
                "?action=query" +
                "&format=json" +
                "&prop=revisions" +
                "&titles=" + encodedArticleName +
                "&rvprop=timestamp%7Cuser" +
                "&rvlimit=4" +
                "&redirects";

        URI uri = URI.create(urlString);
        URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent", "FirstProject/0.1 (academic use; https://example.com)");
        connection.connect();
        return connection;
    }

    private static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    private static void printJson(String jsonData){
        System.out.println(jsonData);
    }
}