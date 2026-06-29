package Gmail;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OTPReader {

    public static String getOTP(long startTime) throws Exception {

        Gmail service = GmailService.getService();

        List<Message> messages = service.users()
                .messages()
                .list("me")
                .setQ("from:(booking.com)")
                .setMaxResults(20L)
                .execute()
                .getMessages();


        if (messages == null || messages.isEmpty()) {
            return null;
        }


        Message latest = null;
        long latestDate = 0;


        for (Message m : messages) {

            Message full = service.users()
                    .messages()
                    .get("me", m.getId())
                    .execute();


            System.out.println(
                    "Message ID: " + full.getId()
                            + " Date: " + full.getInternalDate()
            );


            if (full.getInternalDate() > startTime
                    && full.getInternalDate() > latestDate) {

                latestDate = full.getInternalDate();
                latest = full;
            }
        }

        if (latest == null) {
            return null;
        }


        String body = getMessageBody(latest);


        System.out.println("LATEST EMAIL BODY:");
        System.out.println(body);


        Matcher matcher = Pattern.compile("[A-Z0-9]{6}")
                .matcher(body);


        if (matcher.find()) {
            return matcher.group();
        }


        return null;
    }



    private static String getMessageBody(Message message) {

        if (message.getPayload().getParts() == null) {
            return message.getPayload()
                    .getBody()
                    .getData();
        }


        for (var part : message.getPayload().getParts()) {

            if (part.getBody().getData() != null) {
                return new String(
                        java.util.Base64.getUrlDecoder()
                                .decode(part.getBody().getData()),
                        StandardCharsets.UTF_8
                );
            }
        }

        return "";
    }
}