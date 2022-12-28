package com.popeye.backend.configurations;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfiguration {
    @Bean
    public Firestore getDB() {
        return FirestoreClient.getFirestore(); //die aktuelle Instanz mit der wir arbeiten können
    }
}
