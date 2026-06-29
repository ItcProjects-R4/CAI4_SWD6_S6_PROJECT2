package Gmail;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Collections;


public class GmailService {


    private static final String APPLICATION_NAME = "Booking Automation";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();


    public static Gmail getService() throws Exception {


        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();


        var input = GmailService.class.getResourceAsStream("/credentials.json");


        if(input == null){
            throw new RuntimeException("credentials.json not found");
        }


        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(input));


        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, secrets, Collections.singleton(GmailScopes.GMAIL_READONLY)).setDataStoreFactory(new FileDataStoreFactory(new File("tokens"))).build();
        var credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");


        return new Gmail.Builder(httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();

    }

}