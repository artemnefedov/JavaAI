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

package io.github.artemnefedov.javaai.model.dalle;

import io.github.artemnefedov.javaai.model.Config;
import io.github.artemnefedov.javaai.model.OpenAiModel;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * dto to send a request to the <a
 * href="https://platform.openai.com/docs/api-reference/images/create">Create image</a>.
 */
public class Dalle implements OpenAiModel {

    private final URL URL;
    private DalleConfig config;
    private String prompt;

    public Dalle() {
        try {
            this.URL = new URI("https://api.openai.com/v1/images/generations").toURL();
        } catch (MalformedURLException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        this.config = new DalleConfig(
                DalleConfig.DalleModel.DALL_E_2,
                1,
                "standard",
                DalleConfig.ResponseFormat.URL,
                DalleConfig.Size.D1_D3_1024x1024,
                DalleConfig.Style.VIVID,
                UUID.randomUUID().toString()
        );
    }

    @Override
    public DalleConfig getConfig() {
        return config;
    }

    @Override
    public void setConfig(Config config) {
        this.config = (DalleConfig) config;
    }

    @Override
    public JSONObject getJson() {
        var params = config.getParamJson();
        params.put("prompt", prompt);
        return params;
    }

    @Override
    public String getResponse(JSONObject json) {
        var data = json.getJSONArray("data");
        return data.getJSONObject(0).getString(config.responseFormat().getFormat());
    }

    public List<String> getResponses(JSONObject json) {
        List<String> response = new ArrayList<>();
        var data = json.getJSONArray("data");
        for (int i = 0; i < this.config.n(); i++) {
            response.add(data.getJSONObject(i).getString(config.responseFormat().getFormat()));
        }
        return response;
    }

    @Override
    public URL getURL() {
        return this.URL;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getN() {
        return this.config.n();
    }
}
