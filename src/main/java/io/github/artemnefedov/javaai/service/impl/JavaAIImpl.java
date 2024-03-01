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
import io.github.artemnefedov.javaai.model.chat.Chat;
import io.github.artemnefedov.javaai.model.chat.ChatConfig;
import io.github.artemnefedov.javaai.model.chat.ChatMessage;
import io.github.artemnefedov.javaai.model.dalle.Dalle;
import io.github.artemnefedov.javaai.model.dalle.DalleConfig;
import io.github.artemnefedov.javaai.model.tts.Tts;
import io.github.artemnefedov.javaai.model.tts.TtsConfig;
import io.github.artemnefedov.javaai.service.JavaAI;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * The main class for interacting with JavaAI implements the {@link JavaAI} interface.
 */
public class JavaAIImpl implements JavaAI {

    private final Dalle dalle;
    private final Chat chat;
    private final Tts tts;
    private final ConnectionImpl connection;

    /**
     * Instantiates a new OpenAi implementation.
     *
     * @param apiKey the api key
     */
    public JavaAIImpl(String apiKey) {
        this.connection = new ConnectionImpl(apiKey);
        this.chat = new Chat();
        this.dalle = new Dalle();
        this.tts = new Tts();
    }

    @Override
    public String generateImage(String prompt) {
        this.dalle.setPrompt(prompt);
        var jsonResponse = this.connection.getJsonResponse(this.dalle);
        return this.dalle.getResponse(jsonResponse);
    }

    @Override
    public List<String> generateImages(String prompt) {
        if (this.dalle.getN() <= 1) {
            throw new JavaAIException("You must set the number of responses received from the DALL-E API > 1 to receive multiple responses.");
        }
        this.dalle.setPrompt(prompt);
        var jsonResponse = this.connection.getJsonResponse(this.dalle);
        return this.dalle.getResponses(jsonResponse);
    }

    @Override
    public boolean textToSpeech(String prompt, String path, String fileName) {
        this.tts.setInput(prompt);
        var audioResponse = this.connection.getAudioResponse(this.tts);
        try (var fos = new FileOutputStream(
                String.format("%s%s.%s", path, fileName, this.tts.getResponseFormat()))) {
            fos.write(audioResponse);
            return true;
        } catch (IOException ex) {
            throw new JavaAIException(ex.getMessage());
        }
    }

    @Override
    public String chat(List<ChatMessage> messages) {
        messages.forEach(msg -> this.chat.getMessages().add(msg));
        var jsonResponse = this.connection.getJsonResponse(this.chat);
        return this.chat.getResponse(jsonResponse);
    }

    @Override
    public List<String> chatWithChoices(List<ChatMessage> messages) {
        if (this.chat.getN() <= 1) {
            throw new JavaAIException("You must set the number of responses received from the Chat API > 1 to receive multiple responses.");
        }
        messages.forEach(msg -> this.chat.getMessages().add(msg));
        var jsonResponse = this.connection.getJsonResponse(this.chat);
        return this.chat.getResponses(jsonResponse);
    }

    @Override
    public String chat(String userMessage) {
        this.chat.getMessages().add(new ChatMessage("user", userMessage));
        var jsonResponse = this.connection.getJsonResponse(this.chat);
        return this.chat.getResponse(jsonResponse);
    }

    @Override
    public List<String> chatWithChoices(String userMessage) {
        if (this.chat.getN() <= 1) {
            throw new JavaAIException("You must set the number of responses received from the chat API > 1 to receive multiple responses.");
        }
        this.chat.getMessages().add(new ChatMessage("user", userMessage));
        var jsonResponse = this.connection.getJsonResponse(this.chat);
        return this.chat.getResponses(jsonResponse);
    }

    @Override
    public void setChatConfig(ChatConfig chatConfig) {
        this.chat.setConfig(chatConfig);
    }

    @Override
    public void setDalleConfig(DalleConfig dalleConfig) {
        this.dalle.setConfig(dalleConfig);
    }

    @Override
    public void setTtsConfig(TtsConfig ttsConfig) {
        this.tts.setConfig(ttsConfig);
    }
}
