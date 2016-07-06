package lmadrig.sites.hn;

import lmadrig.sites.Story;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

public class HNStory implements Story {

    @Generated("org.jsonschema2pojo")
    public String by;
    public int descendants;
    public int id;
    public List<Integer> kids = new ArrayList<Integer>();
    public int score;
    public int time;
    public String title;
    public String type;
    public String url;

    @Override
    public URL getUrl() throws MalformedURLException {
        return new URL(this.url);
    }

    @Override
    public String getTitle() {
        return title;
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
