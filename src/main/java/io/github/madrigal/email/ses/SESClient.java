package io.github.madrigal.email.ses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;

public class SESClient {

    final AmazonSimpleEmailServiceClient client;

    public SESClient(AWSCredentialsProvider provider) {
        AWSCredentials credentials;
        try {
            credentials = provider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~/.aws/credentials), and is in valid format.",
                e);
        }
        AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
        Region REGION = Region.getRegion(Regions.US_WEST_2);
        client.setRegion(REGION);
        this.client = client;
    }

    public void sendEmail(MimeMessage message) throws IOException, MessagingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        message.writeTo(outputStream);

        RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
        SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
        client.sendRawEmail(rawEmailRequest);
    }
}
