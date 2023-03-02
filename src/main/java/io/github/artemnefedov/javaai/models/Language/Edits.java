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
 * Edits is a class for interacting with text editing models, you can learn more
 * about the parameters by following
 * <a href="https://platform.openai.com/docs/api-reference/edits">this link</a>.
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
@Getter
@Setter
public class Edits extends OpenAIBase {

    /**
     * API_KEY derived from
     * <a href="https://platform.openai.com/account/api-keys">OpenAI</a>.
     */
    private String API_KEY;

    /**
     * ID of the models to use. You can use the List models API to see all
     * of your available models, or see OpenAI Model overview for descriptions of them.
     */
    private String model = "text-davinci-edit-001";

    /**
     * What sampling temperature to use, between 0 and 2. Higher values
     * like 0.8 will make the output more random, while lower values
     * like 0.2 will make it more focused and deterministic.
     * OpenAI generally recommend altering this or top_p but not both.
     */
    private float temperature = 1.0f;

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
     * Edits constructor with the API key.
     *
     * @param API_KEY key derived from
     *                <a href="https://platform.openai.com/account/api-keys">OpenAI</a>.
     */
    public Edits(String API_KEY) {

        this.API_KEY = API_KEY;
    }

    /**
     * The editsText() method creates a new edit for the provided input data,
     * instructions, and parameters.
     *
     * @param input the input text to use as a starting point for the edit.
     * @param instruction the instruction that tells the model how to edit the prompt.
     * @return the generated text.
     * @throws IOException if there is an error when connecting to the OpenAI API.
     */
    public String editsText(String input, String instruction) throws IOException {

        params.clear();

        params.put("model", getModel());
        params.put("temperature", getTemperature());
        params.put("top_p", getTop_p());
        params.put("n", getN());
        params.put("input", input);
        params.put("instruction", instruction);

        params.put("API", getAPI_KEY());
        params.put("url", getBaseUrl() + Config.getInstance().getProperties("url.openai.edits"));
        params.put("typeResponse", "language");

        return enquire(params);
    }
}