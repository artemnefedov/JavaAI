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

package io.github.artemnefedov.javaai.service;

import io.github.artemnefedov.javaai.exception.JavaAIException;
import io.github.artemnefedov.javaai.model.chat.ChatConfig;
import io.github.artemnefedov.javaai.model.chat.ChatMessage;
import io.github.artemnefedov.javaai.model.dalle.DalleConfig;
import io.github.artemnefedov.javaai.model.tts.TtsConfig;
import io.github.artemnefedov.javaai.service.impl.JavaAIImpl;

import java.util.List;

/**
 * The main class for interacting with JavaAI implements the {@link JavaAI} interface.
 */
public interface JavaAI {

    /**
     * The method
     * that generates single image uses a <a href="https://platform.openai.com/docs/api-reference/images">DALL-E</a>.
     *
     * @param prompt parameters for image generation.
     * @return the response from the API as a string (url or base64 string)
     */
    String generateImage(String prompt);

    /**
     * The method
     * that generates multiple images uses a <a href="https://platform.openai.com/docs/api-reference/images">DALL-E</a>.
     *
     * @param prompt parameters for image generation.
     * @return the response from the API as a string (url or base64 string)
     */
    List<String> generateImages(String prompt);

    /**
     * Generate audio from text using the OpenAI TTS API.
     *
     * @param prompt the text to convert to speech
     * @param path the path to save the file
     * @param fileName the name of the file
     * @return true if the file was created successfully
     */
    boolean textToSpeech(String prompt, String path, String fileName);

    /**
     * Chat with the OpenAi Chat API, returns a single response.
     *
     * @param messages list of messages
     * @return the response from the API as a string
     */
    String chat(List<ChatMessage> messages);

    /**
     * Chat with the OpenAi Chat API, returns a list of responses.
     *
     * @param messages list of messages
     * @return the response from the API as a list of strings
     */
    List<String> chatWithChoices(List<ChatMessage> messages);

    /**
     * Chat with the OpenAi Chat API, returns a single response.
     *
     * @param userMessage the message from the user
     * @return the response from the API as a string
     */
    String chat(String userMessage);

    /**
     * Chat with the OpenAi Chat API, returns a list of responses.
     *
     * @param userMessage the message from the user
     * @return the response from the API as a list of strings
     */
    List<String> chatWithChoices(String userMessage);

    /**
     * JavaAI builder with an api key
     *
     * @param apiKey the api key from OpenAI
     * @return the JavaAI implementation
     */
    static JavaAI javaAiBuilder(String apiKey) {
        return new JavaAIImpl(apiKey);
    }

    /**
     * JavaAI builder with environment variable
     *
     * @return the JavaAI implementation
     */
    static JavaAI javaAiBuilder() {
        String apiKey = System.getProperty("openai.api.key");
        if (apiKey == null) {
            throw new JavaAIException("You must set the OPENAI_API_KEY environment variable to use this library.");
        }
        return new JavaAIImpl(apiKey);
    }

    /**
     * Setter for custom chat configuration options.
     *
     * @param chatConfig record with your options.
     */
    void setChatConfig(ChatConfig chatConfig);

    /**
     * Setter for custom DALL-E configuration options.
     *
     * @param dalleConfig record with your options.
     */
    void setDalleConfig(DalleConfig dalleConfig);

    /**
     * Setter for custom TTS configuration options.
     *
     * @param ttsConfig record with your options.
     */
    void setTtsConfig(TtsConfig ttsConfig);
}
