package com.validator.htmlvalidator.controller;

import com.validator.htmlvalidator.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.validator.htmlvalidator.services.ResultService;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping("/result")
    public String saveResult(@RequestBody Result result) throws ExecutionException, InterruptedException {
        return resultService.saveResult(result);
    }
}
