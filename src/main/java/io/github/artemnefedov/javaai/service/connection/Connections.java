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

package io.github.artemnefedov.javaai.service.connection;

import io.github.artemnefedov.javaai.dto.errors.ErrorDetails;
import io.github.artemnefedov.javaai.exceptionHandling.JavaAIException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static io.github.artemnefedov.javaai.service.connection.Converter.convertModelToJson;
import static io.github.artemnefedov.javaai.service.connection.Converter.convertStreamToModel;

/**
 * The class responsible for communicating with the OpenAI API.
 */
@Getter
@Setter
public class Connections {

    private static String API_KEY;

    public <T> T postStream(Object request, String url, Class<T> response) {

        String json = convertModelToJson(request);

        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {

                String httpResponse = "Unexpected HTTP response: " + connection.getResponseMessage() + "\n";
                ErrorDetails error = convertStreamToModel(connection.getErrorStream(), ErrorDetails.class);

                throw new JavaAIException(httpResponse + error.getErrorDetails());
            }

            return convertStreamToModel(connection.getInputStream(), response);
        } catch (IOException ioException) {

            ioException.printStackTrace();
        }

        return null;
    }

    public void setAPI_KEY(String apiKey) {
        API_KEY = apiKey;
    }
}
