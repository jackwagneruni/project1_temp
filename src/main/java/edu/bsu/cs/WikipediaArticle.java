package edu.bsu.cs;

import java.io.IOException;
import java.net.URISyntaxException;

public class WikipediaArticle {
    private WikipediaAPI api;
    private RevisionParser parser;

    public WikipediaArticle(WikipediaAPI api, RevisionParser parser) {
        this.api = api;
        this.parser = parser;
    }
    public String getArticleName(String userInput) throws IOException, URISyntaxException{
        return userInput;
    }

}
