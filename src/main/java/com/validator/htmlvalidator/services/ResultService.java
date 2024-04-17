package com.validator.htmlvalidator.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.validator.htmlvalidator.models.Result;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ResultService {

    private static final String COLLECTION_NAME = "results";
    public String saveResult(Result result) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFurure = dbFirestore.collection(COLLECTION_NAME).document(result.getMessage()).set(result);

        return collectionApiFurure.get().getUpdateTime().toString();

    }
}
