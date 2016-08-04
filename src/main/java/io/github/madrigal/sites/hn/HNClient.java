package io.github.madrigal.sites.hn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import io.github.madrigal.sites.SiteClient;
import io.github.madrigal.sites.Story;
import io.github.madrigal.requests.Request;

public class HNClient implements SiteClient {
    private static final String BASE_URL = "https://hacker-news.firebaseio.com/v0/";
    private static final String TOP_STORIES_PATH = "topstories.json";

    @Override
    public List<Story> getTopStories() {
        List<Integer> storyIds = getTopStoriesWebsite();
        // TODO get stories that weren't found

        List<Story> stories = storyIds.stream().parallel().map(
            id -> (Story)getStory(id))
            .collect(Collectors.toList());
        return stories;
    }

    private static HNStory getStory(int id) {
        String url = String.format("%s/item/%s.json", BASE_URL, id);
        String jsonResponse = "";
        try {
            jsonResponse = Request.Get(url);
            Gson gson = new Gson();
            return gson.fromJson(jsonResponse, HNStory.class);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to get story "+ Integer.toString(id), e);
        } catch (JsonSyntaxException e) {
            System.out.println("Couldn't parse " + jsonResponse);
        }
        return null;
    }

    private List<Integer> getTopStoriesWebsite() {
        try {
            final String res = Request.Get(BASE_URL + TOP_STORIES_PATH);
            Gson gson = new Gson();
            return Arrays.asList(gson.fromJson(res, Integer[].class));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
