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

import io.github.artemnefedov.javaai.dto.image.ImageData;
import io.github.artemnefedov.javaai.dto.image.request.ImageBuilder;
import io.github.artemnefedov.javaai.dto.image.response.ImageModelResponse;
import io.github.artemnefedov.javaai.dto.language.request.Chat;
import io.github.artemnefedov.javaai.dto.language.response.ChatResponse;
import io.github.artemnefedov.javaai.service.connection.Connections;
import io.github.artemnefedov.javaai.dto.language.ChatMessage;
import io.github.artemnefedov.javaai.dto.language.request.Completions;
import io.github.artemnefedov.javaai.dto.language.response.LanguageModelResponse;
import io.github.artemnefedov.javaai.service.OpenAI;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class for interacting with JavaAI implements the {@link OpenAI} interface.
 */
@Getter
@Setter
public class OpenAIImplementation implements OpenAI {

    private String baseURL = "https://api.openai.com";
    private String compURL = baseURL + "/v1/completions";
    private String imgBuilderURL = baseURL + "/v1/images/generations";
    private String chatURL = baseURL + "/v1/chat/completions";

    protected Completions completions;
    protected ImageBuilder imageBuilder;
    protected Chat chat;

    protected final Connections connections = new Connections();

    public OpenAIImplementation(String API_KEY) {

        this.connections.setAPI_KEY(API_KEY);
    }

    @Override
    public String generateText(String prompt) {

        if (this.completions == null) {
            defaultCompetitionsConfig();
        }

        completions.setPrompt(prompt);

        LanguageModelResponse languageResponse = connections
                .postStream(completions, compURL, LanguageModelResponse.class);

        return languageResponse.getChoices().get(0).getText().trim();
    }

    @Override
    public List<String> generateImage(String prompt) {

        List<String> images = new ArrayList<>();

        if (this.imageBuilder == null) {

            defaultImageBuilderConfig();
        }

        imageBuilder.setPrompt(prompt);

        ImageModelResponse imageResponse = connections
                .postStream(imageBuilder, imgBuilderURL, ImageModelResponse.class);


            if (imageBuilder.getResponse_format().equals("url")) {

                for (ImageData data : imageResponse.getData()) {

                    images.add(data.getUrl());
                }
            } else {

                for (ImageData data : imageResponse.getData()) {

                    images.add(data.getB64Json());
                }
            }

        return images;
    }


    @Override
    public String chat(List<ChatMessage> messages) {

        if (this.chat == null) {

            defaultChatConfig();
        }

        chat.setMessages(messages);

        ChatResponse chatResponse = connections
                .postStream(chat, chatURL, ChatResponse.class);

        return chatResponse.getChoices().get(0).getMessage().getContent().trim();
    }

    @Override
    public void customCompetitionsConfig(Completions completions) {

        this.completions = completions;
    }

    @Override
    public void customImageBuilderConfig(ImageBuilder imageBuilder) {

        this.imageBuilder = imageBuilder;
    }

    @Override
    public void customChatConfig(Chat chat) {

        this.chat = chat;
    }

    @Override
    public void defaultCompetitionsConfig() {

        Completions dafaultCompletions = new Completions();

        dafaultCompletions.setModel("text-davinci-003");
        dafaultCompletions.setMax_tokens(2000);
        dafaultCompletions.setTemperature(0.9f);
        dafaultCompletions.setN((byte) 1);
        dafaultCompletions.setBest_of(1);

        this.completions = dafaultCompletions;
    }

    @Override
    public void defaultImageBuilderConfig() {

        ImageBuilder defaultImgBuilder = new ImageBuilder();

        defaultImgBuilder.setN(1);
        defaultImgBuilder.setSize("1024x1024");
        defaultImgBuilder.setResponse_format("url");

        this.imageBuilder = defaultImgBuilder;
    }

    @Override
    public void defaultChatConfig() {

        Chat defaultChat = new Chat();

        defaultChat.setModel("gpt-3.5-turbo");
        defaultChat.setMax_tokens(2000);
        defaultChat.setN(1);

        this.chat = defaultChat;
    }
}
