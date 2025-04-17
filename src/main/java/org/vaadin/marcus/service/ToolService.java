package org.vaadin.marcus.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ToolService {

    public static String getAdvice() {
        String apiUrl = "https://api.adviceslip.com/advice";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONObject("slip").getString("advice");
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch advice.";
        }
    }

    public static String getJoke() {
        String apiUrl = "https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getString("joke");
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch joke.";
        }
    }

    public static String getCatFact() {
        String apiUrl = "https://catfact.ninja/fact";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getString("fact");
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch cat fact.";
        }
    }

    public static String getQuote() {
        String apiUrl = "https://api.quotable.io/random";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            return "\"" + jsonResponse.getString("content") + "\" - " + jsonResponse.getString("author");
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch quote.";
        }
    }

    public static String getNumberFact(int number) {
        String apiUrl = "http://numbersapi.com/" + number + "/trivia?json";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getString("text");
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch number fact.";
        }
    }

    public static String getWeather(String city) {
        String apiUrl = "https://wttr.in/" + city + "?format=j1";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject current = jsonResponse.getJSONArray("current_condition").getJSONObject(0);
            String temp = current.getString("temp_C");
            String desc = current.getJSONArray("weatherDesc").getJSONObject(0).getString("value");
            String humidity = current.getString("humidity");

            return "Current weather in " + city + ": " + desc + ", Temperature: " + temp + "°C, Humidity: " + humidity + "%";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch weather for " + city + ".";
        }
    }
}
