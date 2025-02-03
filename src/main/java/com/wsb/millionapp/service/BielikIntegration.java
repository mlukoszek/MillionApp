package com.wsb.millionapp.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.wsb.millionapp.to.QuestionDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BielikIntegration {

    public String createRequest(QuestionDto questionDto) throws IOException {
        String apiUrl = "https://api.friendli.ai/dedicated/v1/completions";
        String friendliToken = "flp_gDy5YaSKCJHCYjIl3sVc09NLIGuWx9Yi6pZBYawFRK7845";
        String endpointId = "wyv5j10qu4i4";

        String question = String.format("Oto symulacja telefonu do przyjaciela z teleturnieju Milionerzy. " +
                "Proszę abyś pomógł graczowi odpowiedzieć na pytanie. Wybierz jedną z podanych możliwych odpowiedzi " +
                "wraz z krótkim uzasadnieniem. Oto pytanie: " + questionDto.getQuestionBody()) + ", możliwe odpowiedzi a: " +
                questionDto.getAnswerA() + " b: " + questionDto.getAnswerB() + " c: " + questionDto.getAnswerC() + " d: " +
                questionDto.getAnswerD();

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + friendliToken);

        String jsonInputString = String.format(
                "{\"model\": \"%s\", \"prompt\": \"%s\", \"min_tokens\": 30, \"max_tokens\": 50, \"top_k\": 32, \"top_p\": 0.8, \"n\": 1, \"no_repeat_ngram\": 3, \"ngram_repetition_penalty\": 1.75}",
                endpointId, question
        );

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray choices = jsonResponse.getJSONArray("choices");
                JSONObject firstChoice = choices.getJSONObject(0);
                connection.disconnect();
                return firstChoice.getString("text");
            }
        }
        return "Nie udało się dodzwonić";
    }
}