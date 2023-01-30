package io.github.madrigal.topic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.github.madrigal.sites.Story;

public class Finder {
    private Finder () {

    }

    public static List<Story> filterByKeyword(List<Story> stories, Collection<String> keywords) {
        return stories.stream().filter(
                        s -> containsTopic(s, keywords))
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

    private static boolean containsTopic(Story story, Collection<String> keywords) {
        for (String k: keywords) {
            if (isRelatedToTopic(story, k)) {
                return true;
            }
        }
        return false;
    }

    public static String normalize(String text) {
        return text.toLowerCase();
    }
}
