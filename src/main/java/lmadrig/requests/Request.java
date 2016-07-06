package lmadrig.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {

    /**
     * So far we only care about the response
     * @param url
     * @return
     * @throws IOException
     */
    static {
        HttpURLConnection.setFollowRedirects(true);
    }

    public static String Get(String url) throws IOException {
        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestProperty("User-Agent", "TopicWatcher-1.0");
        con.setRequestMethod("GET");
        con.setInstanceFollowRedirects(true);
        int statusCode = con.getResponseCode();
        if (shouldRedirect(statusCode)) {
            // here we go
            con = redirectOrDie(con);
        }
        if (!isSuccess(statusCode)) {
            throw new RuntimeException("bad status code " + Integer.toString(statusCode));
        }
        return getResponse(con);
    }

    /**
     * Since Java doesn't redirect by default we need to take care of that
     * ourselves. Will try to redirect, and if can't, blow up
     * @param con
     * @return
     */
    private static HttpURLConnection redirectOrDie(HttpURLConnection con) throws IOException {
        System.out.println("Redirecting");
        String location = con.getHeaderField("Location");
        System.out.println(location);
        URL base     = con.getURL();
        URL next     = new URL(base, location);  // Deal with relative URLs
        System.out.println("new url=" + next.toExternalForm());
        HttpURLConnection newcon = (HttpURLConnection) next.openConnection();
        newcon.setRequestMethod(con.getRequestMethod());
        int code = con.getResponseCode();
        if (shouldRedirect(code)) {
            redirectOrDie((con));
        }
        return newcon;
    }

    private static boolean shouldRedirect(int statusCode) {
        return statusCode == HttpURLConnection.HTTP_MOVED_PERM ||
            statusCode == HttpURLConnection.HTTP_MOVED_TEMP;
    }

    public static boolean isSuccess(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    private static String getResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
