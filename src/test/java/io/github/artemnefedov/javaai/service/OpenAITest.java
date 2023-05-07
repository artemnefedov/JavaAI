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

import io.github.artemnefedov.javaai.dto.language.ChatMessage;
import io.github.artemnefedov.javaai.dto.language.request.Completions;
import io.github.artemnefedov.javaai.service.impl.OpenAIImplementation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class OpenAITest {

    OpenAI openAI = new OpenAIImplementation("API_KEY");

    @Test
    void generateTextTest() {

        assertEquals("This is indeed a test.", openAI.generateText("Say this is a test"));
    }

    @Test
    void generateImageTest() {

        List<String> response = openAI.generateImage("A cute baby sea otter");

        for (String url: response) {
            assertTrue( url != null && url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"));
        }

    }

    @Test
    void chatTest() {

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("user", "What is the name of the capital of Armenia?"));

        assertEquals("The capital of Armenia is Yerevan.", openAI.chat(messages));
    }

    @Test
    void customCompetitionsConfigTest() {

        Completions completions =
                new Completions("text-curie-001", 7, 0.1f, (byte) 1, 1);
        openAI.customCompetitionsConfig(completions);

        assertEquals("This is a test.", openAI.generateText("Say this is a test"));
    }

    @AfterEach
    void clearOpenAI() {

        openAI = null;
    }
}