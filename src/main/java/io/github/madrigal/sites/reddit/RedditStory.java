package io.github.madrigal.sites.reddit;

import io.github.madrigal.sites.Story;
import io.github.madrigal.sites.reddit.generated.Child;
import io.github.madrigal.sites.reddit.generated.Data_;

public class RedditStory implements Story {

    // There are more things, but we don't care about them
    String id;
    String title;
    String url;
    String permalink;
    int numComments;

    public static String BASE_URL = "https://www.reddit.com";

    public static RedditStory fromChild(Child input) {
        RedditStory story = new RedditStory();
        Data_ data = input.getData();
        story.id = data.getId();
        story.title = data.getTitle();
        story.url = data.getUrl();
        story.permalink = data.getPermalink();
        story.numComments = data.getNumComments();
        return story;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RedditStory{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getCommentsUrl() {
        return BASE_URL + permalink;
    }

    @Override
    public int getNumberOfComments() {
        return numComments;
    }
}
