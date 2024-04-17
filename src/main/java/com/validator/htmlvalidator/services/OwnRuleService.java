package com.validator.htmlvalidator.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.validator.htmlvalidator.models.FirebaseUser;
import com.validator.htmlvalidator.models.OwnRule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
@Service
public class OwnRuleService {
    private static final String COLLECTION_NAME = "users";
    private Firestore dbFirestore = FirestoreClient.getFirestore();

    private FirebaseUser firebaseUser;

    public FirebaseUser getRuleOwner(String email) throws ExecutionException, InterruptedException{
        System.out.println("email: " + email);
        CollectionReference collection = dbFirestore.collection(COLLECTION_NAME);
        Query query = collection.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot querySnapshot = future.get();
        if (!querySnapshot.isEmpty()) {
            DocumentSnapshot document = querySnapshot.getDocuments().get(0);
            this.firebaseUser = document.toObject(FirebaseUser.class);
            System.out.println("Document data: " + document.getData());
        } else {
            System.out.println("No such document!");
        }
        return this.firebaseUser;
    }
    public String saveOwnRule(OwnRule ownRule, String email) throws ExecutionException, InterruptedException {
        System.out.println("asd "+ownRule.getAttributes());
        FirebaseUser tempFirebaseUser = this.getRuleOwner(email);

        if (tempFirebaseUser == null){
            throw new RuntimeException("Firebase userObject is null!");
        }
        List<OwnRule> tempList = tempFirebaseUser.getOwnRules();
        tempList.add(ownRule);
        tempFirebaseUser.setOwnRules(tempList);
        ApiFuture<WriteResult> collectionApiFurure = dbFirestore.collection(COLLECTION_NAME).document(email).set(tempFirebaseUser);

        return collectionApiFurure.get().getUpdateTime().toString();

    }

    public List<OwnRule> getOwnRules(String email) throws ExecutionException, InterruptedException{
        System.out.println("email: " + email);
        CollectionReference collection = dbFirestore.collection(COLLECTION_NAME);
        Query query = collection.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot querySnapshot = future.get();
        if (!querySnapshot.isEmpty()) {
            DocumentSnapshot document = querySnapshot.getDocuments().get(0);
            this.firebaseUser = document.toObject(FirebaseUser.class);
            System.out.println("Document data: " + document.getData());
        } else {
            System.out.println("No such document!");
        }
        return this.firebaseUser.getOwnRules();
    }

    public void deleteOwnRule(String email, OwnRule ruleToDelete) throws ExecutionException, InterruptedException {
        System.out.println("asd "+ruleToDelete.getRuleName());
        FirebaseUser tempFirebaseUser = this.getRuleOwner(email);

        if (tempFirebaseUser == null){
            throw new RuntimeException("Firebase userObject is null!");
        }
        List<OwnRule> tempList = tempFirebaseUser.getOwnRules();
        System.out.println(tempList.size());
        System.out.println(tempList.remove(ruleToDelete));
        System.out.println(tempList.size());
        tempFirebaseUser.setOwnRules(tempList);
        dbFirestore.collection(COLLECTION_NAME).document(email).set(tempFirebaseUser);

    }
}
