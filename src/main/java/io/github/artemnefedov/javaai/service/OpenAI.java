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

import io.github.artemnefedov.javaai.dto.image.request.ImageBuilder;
import io.github.artemnefedov.javaai.dto.language.ChatMessage;
import io.github.artemnefedov.javaai.dto.language.request.Chat;
import io.github.artemnefedov.javaai.dto.language.request.Completions;

import java.util.List;

public interface OpenAI {

    public String generateText(String prompt);

    public String generateImage(String prompt);

    public String chat(List<ChatMessage> messages);

    public void customCompetitionsConfig(Completions completions);

    public void customImageBuilderConfig(ImageBuilder imageBuilder);

    public void customChatConfig(Chat chat);

    public void defaultCompetitionsConfig();

    public void defaultImageBuilderConfig();

    public void defaultChatConfig();


}
