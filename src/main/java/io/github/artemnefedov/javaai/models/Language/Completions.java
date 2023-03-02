/**
 * MIT License
 *
 * Copyright (c) 2023 Artyom Nefedov
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

package io.github.artemnefedov.javaai.models.Language;

import io.github.artemnefedov.javaai.models.OpenAIBase;
import io.github.artemnefedov.javaai.util.Config;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * Completions is a class for interacting with text completion models, you can learn more
 * about the parameters by following
 * <a href="https://platform.openai.com/docs/api-reference/completions/create">this link</a>.
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
@Getter
@Setter
public class Completions extends OpenAIBase {

    /**
     * API_KEY derived from
     * <a href="https://platform.openai.com/account/api-keys">OpenAI</a>.
     */
    private String API_KEY;

    /**
     * ID of the models to use. You can use the List models API to see all
     * of your available models, or see OpenAI Model overview for descriptions of them.
     */
    private String model;

    /**
     * The suffix that comes after a completion of inserted text.
     */
    private String suffix = null;

    /**
     * What sampling temperature to use, between 0 and 2. Higher values
     * like 0.8 will make the output more random, while lower values
     * like 0.2 will make it more focused and deterministic.
     * OpenAI generally recommend altering this or top_p but not both.
     */
    private float temperature;

    /**
     * The maximum number of tokens to generate in the completion.
     * The token count of your prompt plus max_tokens cannot exceed the models'
     * context length. Most models have a context length of 2048 tokens
     * (except for the newest models, which support 4096).
     */
    private int maxTokens;

    /**
     * An alternative to sampling with temperature, called nucleus sampling,
     * where the models consider the results of the tokens with top_p
     * probability mass. So 0.1 means only the tokens comprising
     * the top 10% probability mass are considered.
     * OpenAI generally recommend altering this or temperature but not both.
     */
    private byte top_p = 1;

    /**
     * How many completions to generate for each prompt.
     * <b>Note:</b> Because this parameter generates many completions,
     * it can quickly consume your token quota. Use carefully
     * and ensure that you have reasonable settings for max_tokens and stop.
     */
    private byte n = 1;

    /**
     * Whether to stream back partial progress. If set, tokens will be sent
     * as data-only server-sent events as they become available, with the stream
     * terminated by a data: [DONE] message.
     */
    private boolean stream = false;

    /**
     * Include the log probabilities on the logprobs most likely tokens,
     * as well the chosen tokens. For example, if logprobs is 5, the API
     * will return a list of the 5 most likely tokens. The API will always
     * return the logprob of the sampled token, so there may be up to
     * logprobs+1 elements in the response.
     * The maximum value for logprobs is 5. If you need more than this,
     * please contact us through OpenAI Help center and describe your use case.
     */
    private int logprobs = 0;

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     */
    private String stop = "\\n";


    /**
     * Echo back the prompt in addition to the completion.
     */
    private boolean echo = false;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new
     * tokens based on whether they appear in the text so far,
     * increasing the models' likelihood to talk about new topics.
     */
    private float presence_penalty = 0;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens
     * based on their existing frequency in the text so far, decreasing the
     * models' likelihood to repeat the same line verbatim.
     */
    private float frequency_penalty = 0;

    /**
     * Completions constructor with the API key.
     *
     * @param API_KEY key derived from
     *                <a href="https://platform.openai.com/account/api-keys">OpenAI</a>.
     */
    public Completions(String API_KEY) {

        this.API_KEY = API_KEY;
        this.model = "text-davinci-003";
        this.temperature = 0.9f;
        this.maxTokens = 4000;
    }

    /**
     * The generateText() method completes the text for the provided hint and parameters.
     *
     * @param prompt the prompt(s) to generate completions for, encoded as a string
     * @return the generated text.
     * @throws IOException if there is an error when connecting to the OpenAI API.
     */
    public String generateText(String prompt) throws IOException {

        params.clear();

        params.put("model", getModel());
        params.put("prompt", prompt);
        params.put("temperature", getTemperature());
        params.put("max_tokens", getMaxTokens());
        params.put("suffix", getSuffix());
        params.put("top_p", getTop_p());
        params.put("n", getN());
        params.put("stream", this.stream);
        params.put("logprobs", getLogprobs());
        params.put("stop", getStop());
        params.put("echo", this.echo);
        params.put("presence_penalty", getPresence_penalty());
        params.put("frequency_penalty", getFrequency_penalty());

        params.put("API", getAPI_KEY());
        params.put("url", getBaseUrl() + Config.getInstance().getProperties("url.openai.completions"));
        params.put("typeResponse", "language");

        return enquire(params);
    }
}