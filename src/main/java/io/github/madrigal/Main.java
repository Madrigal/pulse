package io.github.madrigal;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.runtime.Context;

import io.github.madrigal.email.EmailHelper;
import io.github.madrigal.email.Formatter;
import io.github.madrigal.email.ses.SESClient;
import io.github.madrigal.sites.SiteClient;
import io.github.madrigal.sites.Story;
import io.github.madrigal.sites.hn.HNClient;
import io.github.madrigal.sites.reddit.RedditClient;
import io.github.madrigal.topic.Finder;

public class Main {

    private static final String MY_TOPIC = "Amazon";
    // This needs to match what is setup on SES
    private static final String EMAIL = "email@domain.com";

    // Called via the command line
    public static void main(String[] args) {
        doIt(new ProfileCredentialsProvider());
    }

    // Called via Amazon Lambda Lambda
    public void handler(Context context) {
        doIt(new EnvironmentVariableCredentialsProvider());
    }

    public static void doIt(AWSCredentialsProvider provider) {
        try {
            List<SiteClient> clients = Arrays.asList(new HNClient(), new RedditClient());
            StringBuilder b = new StringBuilder();
            clients.stream().map(
                x -> getRelevantStories(x))
                .forEach(s -> b.append(Formatter.format(s)));
            sendEmail(provider, b.toString());
            System.out.println(b.toString());
        } catch (Throwable e) {
            try {
                // Try to tell what went wrong
                sendEmail(provider, e.toString());
            }
            catch (MessagingException | IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private static void sendEmail(AWSCredentialsProvider provider,
            String messageInput) throws MessagingException, IOException{
        MimeMessage message = EmailHelper.newMessage("Test", EMAIL);
        message.setContent(messageInput, "text/html; charset=utf-8");
        SESClient client = new SESClient(provider);
        client.sendEmail(message);
    }

    private static List<Story> getRelevantStories(SiteClient client) {
        List<Story> stories = client.getTopStories();
        return Finder.filterByKeyword(stories, MY_TOPIC);
    }
}
