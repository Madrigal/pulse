package io.github.madrigal.sites.reddit;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import io.github.madrigal.sites.SiteClient;
import io.github.madrigal.sites.Story;
import io.github.madrigal.requests.Request;

public class RedditClient implements SiteClient {

    public static String BASE_URL = "https://www.reddit.com";
    public static String PROGRAMMING = "programming";

    @Override
    public List<Story> getTopStories() {
        String url = buildTopSubredditUrl(PROGRAMMING);
        String jsonResponse = "";
        try {
            jsonResponse = Request.Get(url);
            Gson gson = new Gson();
            GsonBuilder builder = new GsonBuilder();
            RedditResponse response = builder.create().fromJson(jsonResponse, RedditResponse.class);
            return response.getData().getChildren().stream().map((x) -> RedditStory.fromChild(x)).collect(Collectors.toList());
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to get story ");
        } catch (JsonSyntaxException e) {
            System.out.println("Couldn't parse " + jsonResponse);
            throw new RuntimeException(e);
        }
    }

    public String buildTopSubredditUrl(String subreddit) {
        return String.format("%s/r/%s.json", BASE_URL, subreddit);
    }
}
