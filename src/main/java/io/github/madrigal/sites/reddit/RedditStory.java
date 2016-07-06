package io.github.madrigal.sites.reddit;

import java.net.MalformedURLException;
import java.net.URL;

import io.github.madrigal.sites.Story;
import io.github.madrigal.sites.reddit.generated.Child;
import io.github.madrigal.sites.reddit.generated.Data_;

public class RedditStory implements Story {

    // There are more things, but we don't care about it
    String id;
    String title;
    String url;

    public static RedditStory fromChild(Child input) {
        RedditStory story = new RedditStory();
        Data_ data = input.getData();
        story.id = data.getId();
        story.title = data.getTitle();
        story.url = data.getUrl();
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
    public URL getUrl() throws MalformedURLException {
        return new URL(url);
    }
}
