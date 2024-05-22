package com.validator.htmlvalidator.controller;

import com.validator.htmlvalidator.services.OpenaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ChatController {
    @Autowired
    OpenaiService openaiService;

    @PostMapping("/chatgpt")
    public ResponseEntity<String> sendMessage(@RequestBody String value) {
        String response = null;
        try {
            response = openaiService.getOpenaiResponse(value);

        }catch (Exception e){
            System.err.println("Error when /sendMessage called: "+e.toString());
        }


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
