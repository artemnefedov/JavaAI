/*
 *  MIT License
 *
 *  Copyright (c)  2023. Artyom Nefedov
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package io.github.artemnefedov.javaai.service.impl;

import com.google.gson.Gson;
import io.github.artemnefedov.javaai.dto.OpenAIModel;
import io.github.artemnefedov.javaai.dto.ErrorDetails;
import io.github.artemnefedov.javaai.exceptionhandling.JavaAIException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * The class responsible for communicating with the JavaAI API.
 */
@Slf4j
public class Connection {

    private final String API_KEY;
    private final Gson gson;

    /**
     * Instantiates a new Connection.
     *
     * @param API_KEY the api key
     */
    Connection(String API_KEY) {
        this.API_KEY = API_KEY;
        this.gson = new Gson();
    }

    /**
     * Send post.
     *
     * @param <T>         the type parameter
     * @param openAiModel the openAiModel
     * @param url         the url
     * @param response    the response
     * @return the t
     */
    <T> T sendPost(OpenAIModel openAiModel, URL url, Class<T> response) throws JavaAIException {

        var json = gson.toJson(openAiModel);

        Reader streamReader;

        try {

            var connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            try (var outputStream = connection.getOutputStream()) {
                outputStream.write(gson
                        .toJson(openAiModel)
                        .getBytes(UTF_8));
            }

            if (connection.getResponseCode() != HTTP_OK) {

                var httpResponse = "Unexpected HTTP response: " + connection.getResponseCode() + ' ' + connection.getResponseMessage() + "\n";

                streamReader = new InputStreamReader(connection.getErrorStream(), UTF_8);

                var errors = gson.fromJson(streamReader, ErrorDetails.class);

                throw new JavaAIException(httpResponse + errors.getErrorDetails());
            }

            streamReader = new InputStreamReader(connection.getInputStream(), UTF_8);

        } catch (IOException exception) {

            throw new JavaAIException(exception.getMessage());
        }
        return gson.fromJson(streamReader, response);
    }
}
