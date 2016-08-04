package io.github.madrigal.sites.hn;

import io.github.madrigal.sites.Story;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

public class HNStory implements Story {

    @Generated("org.jsonschema2pojo")
    public String by;
    public int descendants;
    public int id;
    public List<Integer> kids = new ArrayList<>();
    public int score;
    public int time;
    public String title;
    public String type;
    public String url;

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getCommentsUrl() {
        return "https://news.ycombinator.com/item?id=" + Integer.toString(id);
    }

    @Override
    public int getNumberOfComments() {
        return descendants;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HNStory{");
        sb.append("by='").append(by).append('\'');
        sb.append(", descendants=").append(descendants);
        sb.append(", id=").append(id);
        sb.append(", kids=").append(kids);
        sb.append(", score=").append(score);
        sb.append(", time=").append(time);
        sb.append(", title='").append(title).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
