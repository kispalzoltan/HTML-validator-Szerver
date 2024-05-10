package com.validator.htmlvalidator.services;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.validator.htmlvalidator.HtmlValidatorApplication;
import com.validator.htmlvalidator.models.FirebaseUser;
import com.validator.htmlvalidator.models.OwnRule;
import com.validator.htmlvalidator.models.OwnRuleGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
@Service
public class OwnRuleService { 
    private static final String COLLECTION_NAME = "users";
    private final Firestore dbFirestore;

    private FirebaseUser firebaseUser;

    @Autowired
    public OwnRuleService(Firestore firestore) {
        this.dbFirestore = firestore;
    }
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
        System.out.println("asd "+ruleToDelete.toString());
        FirebaseUser tempFirebaseUser = this.getRuleOwner(email);

        if (tempFirebaseUser == null){
            throw new RuntimeException("Firebase userObject is null!");
        }
        List<OwnRule> tempList = tempFirebaseUser.getOwnRules();

        List<OwnRule> newList = new ArrayList<>();
        for(OwnRule rule : tempList){
            if (!Objects.equals(rule.getRuleName(), ruleToDelete.getRuleName())){
                newList.add(rule);
            }
        }
        System.out.println(tempList.size());
        System.out.println(newList.size());
        tempFirebaseUser.setOwnRules(newList);
        dbFirestore.collection(COLLECTION_NAME).document(email).set(tempFirebaseUser);

    }
    public List<OwnRuleGroup> getOwnRuleGroups(String email) throws ExecutionException, InterruptedException{
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
        return this.firebaseUser.getOwnRuleGroups();
    }

    public String saveOwnRuleGroups(OwnRuleGroup ownRuleGroup, String email) throws ExecutionException, InterruptedException {
        FirebaseUser tempFirebaseUser = this.getRuleOwner(email);

        if (tempFirebaseUser == null){
            throw new RuntimeException("Firebase userObject is null!");
        }
        List<OwnRuleGroup> tempList = tempFirebaseUser.getOwnRuleGroups();
        tempList.add(ownRuleGroup);
        tempFirebaseUser.setOwnRuleGroups(tempList);
        ApiFuture<WriteResult> collectionApiFurure = dbFirestore.collection(COLLECTION_NAME).document(email).set(tempFirebaseUser);

        return collectionApiFurure.get().getUpdateTime().toString();

    }
    public void deleteOwnRuleGroup(String email, OwnRuleGroup groupToDelete) throws ExecutionException, InterruptedException {
        System.out.println("asd "+groupToDelete.getGroupName());
        FirebaseUser tempFirebaseUser = this.getRuleOwner(email);

        if (tempFirebaseUser == null){
            throw new RuntimeException("Firebase userObject is null!");
        }
        List<OwnRuleGroup> tempList = tempFirebaseUser.getOwnRuleGroups();

        List<OwnRuleGroup> newList = new ArrayList<>();
        for(OwnRuleGroup group : tempList){
            if (!Objects.equals(group.getGroupName(), groupToDelete.getGroupName())){
                newList.add(group);
            }
        }
        System.out.println(tempList.size());
        System.out.println(newList.size());
        tempFirebaseUser.setOwnRuleGroups(newList);
        dbFirestore.collection(COLLECTION_NAME).document(email).set(tempFirebaseUser);

    }

 
}
