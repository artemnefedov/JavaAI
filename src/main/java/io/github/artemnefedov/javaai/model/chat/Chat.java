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

package io.github.artemnefedov.javaai.model.chat;

import io.github.artemnefedov.javaai.model.Config;
import io.github.artemnefedov.javaai.model.OpenAiModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * dto to send a request to the <a href="https://platform.openai.com/docs/api-reference/chat">Chat</a>.
 */
public class Chat implements OpenAiModel {

    private final URL URL;
    private final List<ChatMessage> messages;
    private ChatConfig config;

    public Chat() {
        try {
            this.URL = new URL("https://api.openai.com/v1/chat/completions");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }

        this.config = new ChatConfig(
                ChatConfig.Model.GPT_3_5_TURBO,
                1F,
                1,
                1,
                false,
                "\n",
                2000,
                0F,
                0F,
                new HashMap<>(),
                UUID.randomUUID().toString()
        );

        this.messages = new ArrayList<>();
    }

    @Override
    public void setConfig(Config config) {
        this.config = (ChatConfig) config;
    }

    @Override
    public JSONObject getJson() {
        var params = this.config.getParamJson();
        var messages = new JSONArray();
        this.messages.forEach(msg -> messages.put(msg.getMap()));
        params.put("messages", messages);
        return params;
    }

    @Override
    public String getResponse(JSONObject json) {
        var choices = json.getJSONArray("choices");

        return choices.getJSONObject(0).getJSONObject("message").getString("content");
    }

    public List<String> getResponses(JSONObject json) {
        List<String> responses = new ArrayList<>();
        var choices = json.getJSONArray("choices");

        for (int i = 0; i < this.config.n(); i++) {
            int index = choices.getJSONObject(i).getInt("index");
            var message = choices.getJSONObject(i)
                    .getJSONObject("message")
                    .getString("content");
            responses.add(index, message);
        }
        return responses;
    }

    @Override
    public URL getURL() {
        return this.URL;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public int getN() {
        return this.config.n();
    }
}
