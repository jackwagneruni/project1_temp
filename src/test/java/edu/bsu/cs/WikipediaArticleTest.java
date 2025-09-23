package edu.bsu.cs;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.*;

public class WikipediaArticleTest {
    @Test
    public void testGetArticleName() throws IOException, URISyntaxException{
        WikipediaAPI api = null;
        RevisionParser parser = null;
        WikipediaArticle article = new WikipediaArticle(api, parser);

        String input = "Frank Zappa";
        String output = article.getArticleName(input);

        assertEquals(input, output, "getArticleName should return input");
    }
}
