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

package io.github.artemnefedov.javaai.service;

import io.github.artemnefedov.javaai.dto.ChatMessage;
import io.github.artemnefedov.javaai.dto.Chat;
import io.github.artemnefedov.javaai.dto.Completions;
import io.github.artemnefedov.javaai.service.impl.JavaAIImplementation;

import java.util.List;

/**
 * The main class for interacting with JavaAI implements the {@link JavaAI} interface.
 */
public interface JavaAI {

    /**
     * The method that generates the text interacts with the
     * <a href="https://platform.openai.com/docs/api-reference/completions">Completions</a>.
     *
     * @param prompt parameters for text generation.
     * @return the response from the API as a string
     */
    String generateText(String prompt);

    /**
     * The method that generates the image interacts with the
     * <a href="https://platform.openai.com/docs/api-reference/images/create">Create image</a>.
     *
     * @param prompt parameters for image generation.
     * @return the response from the API as a string(url)
     */
    String generateImage(String prompt);

    /**
     * The method that generates text, taking into account chat messages,
     * uses a <a href="https://platform.openai.com/docs/api-reference/chat">Chat</a>.
     *
     * @param messages List of {@link ChatMessage}  containing your chat by role.
     * @return the response from the API as a string
     */
    String chat(List<ChatMessage> messages);

    /**
     * The method that generates text, taking into account chat messages,
     * uses a <a href="https://platform.openai.com/docs/api-reference/chat">Chat</a>.
     *
     * @param userMessage your message with the user role.
     * @return the response from the API as a string
     */
    String chat(String userMessage);

    /**
     * Sets your dto to work with the API
     *
     * @param completions dto with your options.
     */
    void setCompletions(Completions completions);

    /**
     * JavaAI builder
     *
     * @param apiKey the api key
     * @return the JavaAI implementation
     */
    static JavaAI javaAiBuilder(String apiKey) {
        return new JavaAIImplementation(apiKey);
    }

    static JavaAI javaAiBuilder() {
        return new JavaAIImplementation();
    }

    /**
     * Sets your dto to work with the API
     *
     * @param chat dto with your options.
     */
    void setChat(Chat chat);

    /**
     * Sets default options for dto, usually this should be enough to get the job done.
     */
    void defaultCompetitionsConfig();

    /**
     * Sets default options for dto, usually this should be enough to get the job done.
     */
    void defaultChatConfig();
}
