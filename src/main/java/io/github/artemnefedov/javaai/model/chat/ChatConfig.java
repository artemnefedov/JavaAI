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
import org.json.JSONObject;

import java.util.Map;

public record ChatConfig(
        Model model,
        float temperature,
        int topP,
        int n,
        boolean stream,
        String stop,
        int maxTokens,
        float presencePenalty,
        float frequencyPenalty,
        Map<Integer, Integer> logitBias,
        String user) implements Config {

    @Override
    public JSONObject getParamJson() {
        var params = new JSONObject();
        params.put("model", this.model().getModel());
        params.put("temperature", this.temperature());
        params.put("top_p", this.topP());
        params.put("n", this.n());
        params.put("stream", this.stream());
        params.put("stop", this.stop());
        params.put("max_tokens", this.maxTokens());
        params.put("presence_penalty", this.presencePenalty());
        params.put("frequency_penalty", this.frequencyPenalty());
        params.put("logit_bias", this.logitBias());
        params.put("user", this.user());
        return params;
    }

    public enum Model {
        GPT_4("gpt-4"),
        GPT_4_TURBO_PREVIEW("gpt-4-turbo-preview"),
        GPT_4_VISION_PREVIEW("gpt-4-vision-preview"),
        GPT_4_32K("gpt-4-32k"),
        GPT_3_5_TURBO("gpt-3.5-turbo"),
        GPT_3_5_TURBO_16K("gpt-3.5-turbo-16k");

        private final String model;

        Model(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }
    }
}
