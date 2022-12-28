package com.popeye.backend.repos;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.popeye.backend.configurations.FirebaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class FirebaseRepository {
    //Crud create read update delete REPOSITORY verstehen von maxl videos

    Logger logger = LoggerFactory.getLogger(FirebaseRepository.class);

    @Autowired
    private FirebaseConfiguration firebaseConfig;

    public String createWithoutDocumentId(String collection, Object object) {
        //wir fügen dem der Sammlung ein Objekt hinzu ohne Dokument ID
        ApiFuture<DocumentReference> collectionApiFuture = firebaseConfig.getDB().collection(collection).add(object);
        DocumentReference documentReference = (DocumentReference) getDocumentReferences(collectionApiFuture, collection);
        if (documentReference != null) {
            return documentReference.getId();
        } else {
            return null;
        }
    }

    public void createWithDocumentId(String collection, String documentId, Object object) {
        ApiFuture<WriteResult> collectionApiFuture = firebaseConfig.getDB().collection(collection).document(documentId).set(object);
        WriteResult writeResult = (WriteResult) getDocumentReferences(collectionApiFuture, collection);
        if (writeResult.getUpdateTime() != null) {
            return;
        } else {
            return;
        }
    }

    private Object getDocumentReferences(ApiFuture<?> apiFuture, String collection) {
        try {
            return apiFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(String.format("No Document in %s with the ", collection));
            return null;
        }
    }

    public List<?> getAllDocumentsByCollection(String collection, Class<?> expextedClass) {
        ApiFuture<QuerySnapshot> documentReference = firebaseConfig.getDB().collection(collection).get();
        QuerySnapshot resultsAsSnapshot = (QuerySnapshot) getDocumentReferences(documentReference, collection);
        if (resultsAsSnapshot.getDocuments() != null) {
            return resultsAsSnapshot.getDocuments().stream().map(curr -> curr.toObject(expextedClass)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }


    public Object getDocumentByDocumentId(String collection, String documentId, Class expectedClass) {
        DocumentSnapshot documentReference;
        documentReference = (DocumentSnapshot) getDocumentReferences(firebaseConfig.getDB().collection(collection).document(documentId).get(), collection);
        if (documentReference.exists()) {
            return documentReference.toObject(expectedClass);
        } else {
            return null;
        }
    }

    public void updateObjectByDocumentId(String collection, String documentId, Object object) {
        if (getDocumentByDocumentId(collection, documentId, object.getClass()) != null) {
            createWithDocumentId(collection, documentId, object);
        } else {
            logger.error("could not update");
        }
    }


    public void deleteObjectByDocumentId(String collection, String documentId) {
        ApiFuture<WriteResult> documentReference = firebaseConfig.getDB().collection(collection).document(documentId).delete();
        WriteResult writeResult = (WriteResult) getDocumentReferences(documentReference, collection);
        if (writeResult.getUpdateTime() != null) {
            logger.info("deleted");
        } else {
            logger.info("could not delete");
        }
    }
}
