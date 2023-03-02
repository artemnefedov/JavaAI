/**
 * MIT License
 *
 * Copyright (c) 2023 Artyom Nefedov
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

package io.github.artemnefedov.javaai.models;

import com.google.gson.Gson;
import io.github.artemnefedov.javaai.models.Image.ImageResponse;
import io.github.artemnefedov.javaai.models.Language.LanguageResponse;
import io.github.artemnefedov.javaai.util.Config;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * A general class for working with the OpenAI API.
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
@Getter
@Setter
public class OpenAIBase {

    /**
     * url address of the OpenAI API.
     */
    private String baseUrl = Config.getInstance().getProperties("url.openai.base");

    /**
     * gson - used to serialize and deserialize JSON.
     */
    protected static Gson gson = new Gson();

    /**
     * Set of model parameters.
     */
    public Map<String, Object> params = new HashMap<>();

    /**
     * The enquire() method is used to create a connection to the API
     *
     * @param params Map with parameters for the model.
     * @return The response from the API as a string.
     * @throws IOException if there is an error when connecting to the OpenAI API.
     */
    public static String enquire(Map<String, Object> params) throws IOException {

        String API_KEY = (String) params.get("API");
        params.remove("API");

        String url = (String) params.get("url");
        params.remove("url");

        String typeResponse = (String) params.get("typeResponse");
        params.remove("typeResponse");

        HttpURLConnection connection  = (HttpURLConnection) new URL(url)
                .openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(gson.toJson(params).getBytes(StandardCharsets.UTF_8));

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {

            String errorMessage = "Unexpected HTTP response: " +
                    connection.getResponseMessage() + "\n" +
                    "Error details: " + readStream(connection.getErrorStream());
            throw new IOException(errorMessage);
        }

        if (typeResponse.equals("image")) {

            ImageResponse modelResponse = convertStreamToModel(
                    connection.getInputStream(), ImageResponse.class);
            return modelResponse.getResponse();
        } else {

            LanguageResponse modelResponse = convertStreamToModel(
                    connection.getInputStream(), LanguageResponse.class);
            return modelResponse.getResponse();
        }
    }

    /**
     * The convertStreamToModel() method convert API input stream to the relevant class models.
     *
     * @param stream input stream from the called API.
     * @param tClass the class type of the response models.
     * @return an instance of the provided class type, T, that represents
     * the API response.
     */
    public static <T> T convertStreamToModel(InputStream stream, Class<T> tClass) {

        Reader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        return gson.fromJson(streamReader, tClass);
    }

    /**
     * The readStream() method read the input stream, if there are errors, and return them as a string.
     *
     * @param stream the input stream to read.
     * @return the contents of the input stream as a string.
     * @throws IOException if there is an issue reading the input stream.
     */
    public static String readStream(InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains(":") & !line.contains("{")) {

                    line = "\n\t\t" + line;
                    result.append(line);
                }
            }
        }
        return result.toString();
    }
}
