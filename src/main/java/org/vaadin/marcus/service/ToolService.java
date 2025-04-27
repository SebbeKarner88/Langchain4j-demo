package org.vaadin.marcus.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ToolService {

    // Map to store the state of multi-step processes
    private static final Map<String, Integer> multiStepProcessState = new HashMap<>();

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

    /**
     * A multi-step process to help plan a trip.
     * This method demonstrates how to implement a tool that guides the user through multiple steps.
     * 
     * @param chatId The unique identifier for the chat session
     * @param input The user's input for the current step
     * @return Instructions for the next step or the final result
     */
    public static String planTripMultiStep(String chatId, String input) {
        // Get the current step for this chat session, or start at step 1 if new
        int currentStep = multiStepProcessState.getOrDefault(chatId, 1);

        // Process based on the current step
        switch (currentStep) {
            case 1:
                // First step: Ask for destination
                multiStepProcessState.put(chatId, 2); // Move to next step
                return "Step 1/4: Let's plan your trip! Where would you like to go? (Please provide a city name)";

            case 2:
                // Second step: Ask for duration
                String destination = input.trim();
                // Check weather at the destination
                String weather = getWeather(destination);
                multiStepProcessState.put(chatId, 3); // Move to next step
                return "Step 2/4: " + weather + "\nHow many days are you planning to stay in " + destination + "?";

            case 3:
                // Third step: Ask for budget
                String duration = input.trim();
                multiStepProcessState.put(chatId, 4); // Move to next step
                return "Step 3/4: You're planning a " + duration + " day trip. What's your budget for this trip?";

            case 4:
                // Fourth step: Provide a summary and recommendations
                String budget = input.trim();
                // Reset the state for this chat session
                multiStepProcessState.remove(chatId);
                return "Step 4/4: Based on your budget of " + budget + ", here are some recommendations:\n" +
                       "1. Look for accommodations within " + (Integer.parseInt(budget.replaceAll("[^0-9]", "")) / 3) + " price range per night\n" +
                       "2. Set aside about " + (Integer.parseInt(budget.replaceAll("[^0-9]", "")) / 5) + " for food and dining\n" +
                       "3. Consider local transportation options to save money\n" +
                       "4. Research free or low-cost attractions\n\n" +
                       "Your trip planning is complete! Is there anything else you'd like to know?";

            default:
                // Reset the state if something went wrong
                multiStepProcessState.remove(chatId);
                return "Something went wrong with the trip planning process. Let's start over. Where would you like to go?";
        }
    }
}
