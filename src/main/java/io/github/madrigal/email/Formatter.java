package io.github.madrigal.email;

import java.util.List;

import io.github.madrigal.sites.Story;

public class Formatter {
    public static String format(List<Story> stories) {
        StringBuilder b = new StringBuilder();
        stories.stream().forEach(s -> b.append(build(s) + "\n\n"));
        return b.toString();
    }

    private static String build(Story story) {
        return String.format(
            "Title %s\n"
                + "%s\n"
                + "%s\n"
                + "Comments: %s\n", story.getTitle(),
            story.getUrl(), story.getCommentsUrl(),
            String.valueOf(story.getNumberOfComments())
        );
    }
}
