package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RevisionParser {

    public List<Revision> parse(InputStream jsonStream) {
        String json = convertStreamToString(jsonStream);
        List<Revision> revisions = new ArrayList<>();

        try {
            net.minidev.json.JSONArray revisionsArray = JsonPath.read(json, "$..revisions[*]");

            for (Object revisionObj : revisionsArray) {
                String username = JsonPath.read(revisionObj, "$.user");
                String timestamp = JsonPath.read(revisionObj, "$.timestamp");
                revisions.add(new Revision(username, timestamp));
            }
        } catch (Exception e) {
            // Return empty list if parsing fails
        }

        return revisions;
    }

    public String getRedirectTitle(InputStream jsonStream) {
        String json = convertStreamToString(jsonStream);

        try {
            net.minidev.json.JSONArray redirects = JsonPath.read(json, "$.query.redirects[*]");
            if (redirects != null && !redirects.isEmpty()) {
                return JsonPath.read(redirects.get(0), "$.to");
            }
        } catch (Exception e) {
            // No redirect found
        }

        return null;
    }

    private String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}