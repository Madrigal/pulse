package io.github.madrigal.sites;

import java.net.MalformedURLException;
import java.net.URL;

public interface Story {
    String getTitle();
    URL getUrl() throws MalformedURLException;
}
