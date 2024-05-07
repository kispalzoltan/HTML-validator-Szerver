package com.validator.htmlvalidator.controller;

import com.validator.htmlvalidator.models.HtmlDto;
import com.validator.htmlvalidator.services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/public")
public class HTMLController {

    @Autowired
    private PerformanceService performanceService;
    @GetMapping("/fetch-html")
    public ResponseEntity<HtmlDto> fetchHTML(@RequestParam String url) {
        // Create a RestTemplate to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Fetch HTML content from the given URL
        String htmlContent = restTemplate.getForObject(url, String.class);
        HtmlDto dto = new HtmlDto(htmlContent);
        // Return the fetched HTML content
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/measure-load-time")
    public ResponseEntity<HtmlDto> measureLoadTime(@RequestParam String url) {
        HtmlDto dto = new HtmlDto(Double.toString(performanceService.measureLoadTime(url)));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
