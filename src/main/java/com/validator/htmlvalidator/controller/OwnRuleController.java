package com.validator.htmlvalidator.controller;

import com.google.cloud.firestore.Firestore;
import com.validator.htmlvalidator.models.*;
import com.validator.htmlvalidator.services.OwnRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class OwnRuleController {

    private final Firestore firestore;
    private OwnRuleService ownRuleService;
    @Autowired
    public OwnRuleController(Firestore firestore) {
        this.firestore = firestore;
        this.ownRuleService = new OwnRuleService(firestore);
    }


    @PostMapping("/ownRuleOwner")
    public FirebaseUser getRuleOwner(@RequestBody String email) throws ExecutionException, InterruptedException {
        FirebaseUser firebaseUser = null;
        try {
            firebaseUser = this.ownRuleService.getRuleOwner(email);
        }catch (Exception e){
            System.err.println("Error when /ownRuleOwner called: "+e.toString());
        }

        return firebaseUser;
    }

    @PostMapping("/saveOwnRule")
    public void saveOwnRule(@RequestBody OwnRuleDto ownRuleDto) throws ExecutionException, InterruptedException {
        String temp = null;
        try {
            temp = this.ownRuleService.saveOwnRule(ownRuleDto.ownRule(), ownRuleDto.email());
        }catch (Exception e){
            System.err.println("Error when /saveOwnRule called: "+e.toString());
        }

    }

    @PostMapping("/getOwnRule")
    public List<OwnRule> getOwnRule(@RequestBody String email) throws ExecutionException, InterruptedException {
        List<OwnRule> rules = null;
        try {
            rules = this.ownRuleService.getOwnRules(email);
        }catch (Exception e){
            System.err.println("Error when /getOwnRule called: "+e.toString());
        }

        return rules;
    }

    @PostMapping("/deleteOwnRule")
    public void deleteOwnRule(@RequestBody OwnRuleDto ownRuleDto) throws ExecutionException, InterruptedException {

        try {
            this.ownRuleService.deleteOwnRule(ownRuleDto.email(),ownRuleDto.ownRule());
        }catch (Exception e){
            System.err.println("Error when /deleteOwnRule called: "+e.toString());
        }

    }

    @PostMapping("/getOwnRuleGroups")
    public List<OwnRuleGroup> getOwnRuleGroups(@RequestBody String email) throws ExecutionException, InterruptedException {
        List<OwnRuleGroup> ruleGroups = null;
        try {
            ruleGroups = this.ownRuleService.getOwnRuleGroups(email);
        }catch (Exception e){
            System.err.println("Error when /deleteOwnRule called: "+e.toString());
        }

        return ruleGroups;
    }

    @PostMapping("/saveOwnRuleGroups")
    public String saveOwnRule(@RequestBody OwnRuleGroupDto ownRuleGroupDto) throws ExecutionException, InterruptedException {
        String temp = null;
        try {
            temp = this.ownRuleService.saveOwnRuleGroups(ownRuleGroupDto.ownRuleGroup(), ownRuleGroupDto.email());
        }catch (Exception e){
            System.err.println("Error when /saveOwnRuleGroups called: "+e.toString());
        }

        return temp;
    }

    @PostMapping("/deleteOwnRuleGroup")
    public void deleteOwnRuleGroup(@RequestBody OwnRuleGroupDto ownRuleGroupDto) throws ExecutionException, InterruptedException {
        System.out.println(".................");
        System.out.println(ownRuleGroupDto.ownRuleGroup().getGroupName());
        try {
            this.ownRuleService.deleteOwnRuleGroup(ownRuleGroupDto.email(),ownRuleGroupDto.ownRuleGroup());
        }catch (Exception e){
            System.err.println("Error when /deleteOwnRuleGroup called: "+e.toString());
        }

    } 
    
}
