/*
 * MIT License
 *
 * Copyright (c) 2024 Artyom Nefedov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.artemnefedov.javaai.service.impl;

import io.github.artemnefedov.javaai.exception.JavaAIException;
import io.github.artemnefedov.javaai.model.OpenAiModel;
import io.github.artemnefedov.javaai.service.Connection;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The class responsible for communicating with the JavaAI API.
 */
public class ConnectionImpl implements Connection {

    private final String API_KEY;

    /**
     * Instantiates a new Connection.
     *
     * @param apiKey the api key
     */
    ConnectionImpl(String apiKey) {
        API_KEY = apiKey;
    }

    @Override
    public JSONObject getJsonResponse(OpenAiModel model) {
        var inputStream = sendRequest(model.getJson().toString(), model.getURL());
        String response = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining("\n"));
        return new JSONObject(response);
    }

    @Override
    public byte[] getAudioResponse(OpenAiModel model) {
        var inputStream = sendRequest(model.getJson().toString(), model.getURL());
        try (var byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream sendRequest(String json, URL url) {
        try {
            var connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);
            try (var outputStream = connection.getOutputStream()) {
                outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            }
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                var errorMessages = new StringBuilder()
                        .append("Unexpected HTTP response: ")
                        .append(connection.getResponseCode())
                        .append("\n");
                String errorResponse = new BufferedReader(new InputStreamReader(
                        connection.getErrorStream(),
                        StandardCharsets.UTF_8
                )).lines().collect(Collectors.joining("\n"));
                Map<String, Object> error = new JSONObject(errorResponse).toMap();
                Map<String, Object> errorDetails = (Map<String, Object>) error.get("error");
                errorMessages.append(errorDetails.get("message"));
                throw new JavaAIException(errorMessages.toString());
            }
            return connection.getInputStream();
        } catch (IOException exception) {
            throw new JavaAIException(exception.getMessage());
        }
    }
}
