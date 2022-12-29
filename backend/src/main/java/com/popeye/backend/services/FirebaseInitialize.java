package com.popeye.backend.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FirebaseInitialize {    FileInputStream serviceAccount;
    File file;
    {
        try {
            file = ResourceUtils.getFile("classpath:firebase-configuration-key-popeye.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //FileInputStream self learning
    {
        try {
            serviceAccount = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    FirebaseOptions options;
    //FirebaseOptions.Builder "depricated" but it still used to connect to website
    {
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
