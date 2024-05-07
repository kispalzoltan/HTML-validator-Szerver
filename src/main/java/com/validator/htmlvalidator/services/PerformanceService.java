package com.validator.htmlvalidator.services;

import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PerformanceService {

    public double measureLoadTime(String url) {
        long startTime = System.currentTimeMillis();

        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Hiba esetén visszatérünk -1-gyel
        }

        long endTime = System.currentTimeMillis();
        System.out.println((double) (endTime - startTime) / 1000.0);
        return (double) (endTime - startTime) / 1000.0; // A betöltési idő másodpercben
    }


}
