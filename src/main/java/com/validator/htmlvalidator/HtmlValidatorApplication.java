package com.validator.htmlvalidator;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class HtmlValidatorApplication {


	public static void main(String[] args) {
/*
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
				app = FirebaseApp.initializeApp(options);
			}
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
*/
		SpringApplication.run(HtmlValidatorApplication.class, args);
	}
}
