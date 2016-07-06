package io.github.madrigal;

import java.util.Arrays;
import java.util.List;

import io.github.madrigal.sites.SiteClient;
import io.github.madrigal.sites.Story;
import io.github.madrigal.sites.hn.HNClient;
import io.github.madrigal.sites.reddit.RedditClient;
import io.github.madrigal.topic.Finder;

public class Main {
    final static String myTopic = "Amazon";

    public static void main(String[] args) {
        try {
            List<SiteClient> clients = Arrays.asList(new HNClient(), new RedditClient());
            clients.stream().map(x -> getRelevantStories(x)).forEach(s -> System.out.println(s));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static List<Story> getRelevantStories(SiteClient client) {
        List<Story> stories = client.getTopStories();
        return Finder.filterByKeyword(stories, myTopic);
    }
}
