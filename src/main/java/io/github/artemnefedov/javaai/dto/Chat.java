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

package io.github.artemnefedov.javaai.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * dto to send a request to the <a href="https://platform.openai.com/docs/api-reference/chat">Chat</a>.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat implements OpenAIModel {

    private String model;
    private List<ChatMessage> messages;
    private float temperature;
    @SerializedName("top_p")
    private byte topP;
    private int n;
    private boolean stream;
    private String stop;
    @SerializedName("max_tokens")
    private int maxTokens;
    @SerializedName("presence_penalty")
    private float presencePenalty;
    @SerializedName("frequency_penalty")
    private float frequencyPenalty;
    @SerializedName("logit_bias")
    private Map<String, Integer> logitBias;
    private String user;

    /**
     * Instantiates a new Chat.
     *
     * @param model      the model
     * @param n          the n
     * @param maxTokens the max tokens
     */
    public Chat(String model, int n, int maxTokens) {
        this.model = model;
        this.n = n;
        this.maxTokens = maxTokens;
    }

    @Override
    public Class<? extends ModelResponse> getResponseModel() {
        return ChatResponse.class;
    }

    public record ChatResponse(
            String id,
            String object,
            long created,
            String model,
            List<ChatChoice> choices,
            Usage usage
    ) implements ModelResponse {

        public record ChatChoice(
                int index,
                ChatMessage message,
                String finishReason
        ) {
        }

        record Usage(
                int promptTokens,
                int completionTokens,
                int totalTokens
        ) {
        }

        /**
         * Gets response.
         *
         * @return the response
         */
        public String getResponse() {

            return this.choices().get(0).message().getContent().trim();
        }
    }
}
