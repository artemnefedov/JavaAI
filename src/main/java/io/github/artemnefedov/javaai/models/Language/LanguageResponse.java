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

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * LanguageResponse is a class for formatting the response from the API
 * and outputting the result in a normal form.
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
@Getter
@Setter
public class LanguageResponse {

    /**
     * Set of values returned by the API.
     */
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    /**
     * A nested class that represents a choice object returned in the API
     * response.
     */
    @Getter
    @Setter
    public static class Choice {

        /**
         * Set of values returned by the API.
         */
        private String text;
        private byte index;
        private Object logprobs;
        private String finish_reason;
    }

    /**
     * A nested class that represents a User object returned in the API
     * response.
     */
    @Getter
    @Setter
    public static class Usage {

        /**
         * Set of values returned by the API.
         */
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }

    /**
     * The getResponse() method returns the generated text.
     *
     * @return the generated text.
     */
    public String getResponse() {

        return getChoices().get(0).getText().trim();
    }
}