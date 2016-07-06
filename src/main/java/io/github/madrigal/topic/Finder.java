package io.github.madrigal.topic;

import java.util.List;
import java.util.stream.Collectors;

import io.github.madrigal.sites.Story;

public class Finder {
    private Finder () {

    }

    public static List<Story> filterByKeyword(List<Story> stories, String keyword) {
        return stories.stream().filter(
            s -> isRelatedToTopic(s, keyword))
            .collect(Collectors.toList());
    }

    /**
     * Pretty dumb implementation, may change in the future
     * @param story
     * @param keyword
     * @return
     */
    public static boolean isRelatedToTopic(Story story, String keyword) {
        String title = story.getTitle();
        String normalizedTitle = normalize(title);
        String normalizedKeyword = normalize(keyword);
        return normalizedTitle.contains(normalizedKeyword);
    }

    public static String normalize(String text) {
        return text.toLowerCase();
    }
}
