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
 * dto to send a request to the <a href="https://platform.openai.com/docs/api-reference/completions">Completions</a>.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Completions implements OpenAIModel {

    private String model;
    private String prompt;
    private String suffix;
    @SerializedName("max_tokens")
    private int maxTokens;
    private float temperature;
    @SerializedName("top_p")
    private byte topP;
    private byte n;
    private boolean stream;
    private int logprobs;
    private boolean echo;
    private String stop;
    @SerializedName("presence_penalty")
    private float presencePenalty;
    @SerializedName("frequency_penalty")
    private float frequencyPenalty;
    @SerializedName("best_of")
    private int bestOf;
    @SerializedName("logit_bias")
    private Map<String, Integer> logitBias;
    private String user;

    /**
     * Instantiates a new Completions.
     *
     * @param model       the model
     * @param maxTokens   the max tokens
     * @param temperature the temperature
     * @param n           the n
     * @param bestOf      the best of
     */
    public Completions(String model, int maxTokens, float temperature, byte n, int bestOf) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.n = n;
        this.bestOf = bestOf;
    }

    @Override
    public Class<? extends ModelResponse> getResponseModel() {
        return LanguageModelResponse.class;
    }

    /**
     * The type Language model response.
     */
    public record LanguageModelResponse(
            String id,
            String object,
            long created,
            String model,
            List<Choice> choices,
            Usage usage
    ) implements ModelResponse{
        /**
         * The type Choice.
         */
        record Choice(String text, int index, Object logprobs, String finishReason) {
        }

        /**
         * The type Usage.
         */
        record Usage(int promptTokens, int completionTokens, int totalTokens) {
        }

        /**
         * Gets response.
         *
         * @return the response
         */
        public String getResponse() {
            return this.choices.get(0).text().trim();
        }
    }
}