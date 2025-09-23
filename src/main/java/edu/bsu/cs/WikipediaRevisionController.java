package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.List;

public class WikipediaRevisionController {
    private final UserInput userInput;
    private final UserOutput userOutput;
    private final WikipediaAPI wikipediaAPI;
    private final RevisionParser revisionParser;
    private final BubbleSort bubbleSort;
    private final RevisionFormatter revisionFormatter;

    public WikipediaRevisionController() {
        this.userInput = new UserInput();
        this.userOutput = new UserOutput();
        this.wikipediaAPI = new WikipediaAPI();
        this.revisionParser = new RevisionParser();
        this.bubbleSort = new BubbleSort();
        this.revisionFormatter = new RevisionFormatter();
    }

    public void run() {
        try {
            String articleName = userInput.getArticleInput();

            if (articleName == null || articleName.trim().isEmpty()) {
                userOutput.printError("Error: Please enter a valid article name");
                userOutput.exitWithError();
                return;
            }

            articleName = articleName.trim();
            URLConnection connection = wikipediaAPI.getJSON(articleName);
            InputStream jsonStream = connection.getInputStream();

            String redirectTitle = revisionParser.getRedirectTitle(jsonStream);
            if (redirectTitle != null) {
                userOutput.print(revisionFormatter.formatRedirect(redirectTitle));
            }

            connection = wikipediaAPI.getJSON(articleName);
            jsonStream = connection.getInputStream();
            List<Revision> revisions = revisionParser.parse(jsonStream);

            if (revisions.isEmpty()) {
                userOutput.printError("Error: No revisions found for article '" + articleName + "'");
                userOutput.exitWithError();
                return;
            }

            List<Revision> sortedRevisions = bubbleSort.sort(revisions);
            String formattedOutput = revisionFormatter.format(sortedRevisions);
            userOutput.print(formattedOutput);
            userOutput.exit();

        } catch (IOException e) {
            userOutput.printError("Network error: Unable to connect to Wikipedia.");
            userOutput.exitWithError();
        } catch (URISyntaxException e) {
            userOutput.printError("Error: Invalid article name format");
            userOutput.exitWithError();
        } catch (Exception e) {
            userOutput.printError("An unexpected error occurred: " + e.getMessage());
            userOutput.exitWithError();
        }
    }

    public static void main(String[] args) {
        new WikipediaRevisionController().run();
    }
}