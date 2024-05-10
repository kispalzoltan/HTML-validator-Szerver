package com.validator.htmlvalidator;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;



/*
    @Value("${serviceAccountKey.json}")
    private String firebaseConfigPath;

    @Bean
    public FirebaseApp  initializeFirebase() throws IOException {
        ClassLoader classLoader = HtmlValidatorApplication.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

        try {
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            System.out.println(file.getAbsolutePath());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp app = null;
            if (FirebaseApp.getApps().isEmpty()) {
                app = FirebaseApp.initializeApp(options, "appName");
            } else {
                app = FirebaseApp.initializeApp(options, "appName");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    */
@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebase() throws IOException {
        ClassLoader classLoader = FirebaseConfig.class.getClassLoader();

        URL url = classLoader.getResource("serviceAccountKey.json");
        if (url != null) {
            System.out.println(url.getFile()); // This will print the path to the resource within the JAR
            InputStream serviceAccount = url.openStream(); // Open the stream directly from the URL


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                //System.err.println(FirebaseApp.getInstance().getName());
            }
        }

        return FirebaseApp.getInstance();
    }

    @Bean
    public Firestore firestore() throws IOException {
        return FirestoreClient.getFirestore(firebase());
    }

    @Bean
    public FirebaseAuth firebaseAuth() {
        try {
            return FirebaseAuth.getInstance(firebase());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return FirebaseAuth.getInstance();
    }

}


