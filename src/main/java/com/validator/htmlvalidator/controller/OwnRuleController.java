package com.validator.htmlvalidator.controller;

import com.validator.htmlvalidator.models.FirebaseUser;
import com.validator.htmlvalidator.models.OwnRule;
import com.validator.htmlvalidator.models.OwnRuleDto;
import com.validator.htmlvalidator.models.User;
import com.validator.htmlvalidator.services.OwnRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class OwnRuleController {

    @Autowired
    private OwnRuleService ownRuleService;

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
    public String saveOwnRule(@RequestBody OwnRuleDto ownRuleDto) throws ExecutionException, InterruptedException {
        String temp = null;
        try {
            temp = this.ownRuleService.saveOwnRule(ownRuleDto.ownRule(), ownRuleDto.email());
        }catch (Exception e){
            System.err.println("Error when /saveOwnRule called: "+e.toString());
        }

        return temp;
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
}
