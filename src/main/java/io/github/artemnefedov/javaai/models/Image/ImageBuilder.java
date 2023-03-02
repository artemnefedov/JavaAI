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

package io.github.artemnefedov.javaai.models.Image;

import io.github.artemnefedov.javaai.models.OpenAIBase;
import io.github.artemnefedov.javaai.util.Config;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * ImageBuilder is a class for creating an image by hint, you can learn more
 * about the parameters by following
 * <a href="https://platform.openai.com/docs/api-reference/images/create">this link</a>.
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
@Getter
@Setter
public class ImageBuilder extends OpenAIBase {

    /**
     * API_KEY derived from <a href="https://platform.openai.com/account/api-keys">OpenAI</a>.
     */
    private String API_KEY;


    /**
     * The number of images to generate. Must be between 1 and 10.
     */
    private int n = 1;

    /**
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
     */
    private String size = "1024x1024";

    /**
     * The format in which the generated images are returned.
     * Must be one of url or b64_json.
     */
    private String response_format = "url";

    /**
     * ImageBuilder constructor with the API key.
     *
     * @param API_KEY key derived from <a href="https://platform.openai.com/account/api-keys">OpenAI</a>.
     */
    public ImageBuilder(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    /**
     * The createImage() method creates an image according to a given request.
     *
     * @param prompt A text description of the desired image(s).
     *               The maximum length is 1000 characters.
     * @return url or b64_json, depending on the given response_format.
     * @throws IOException if there is an error when connecting
     * to the OpenAI API.
     */
    public String createImage(String prompt) throws IOException {

        params.clear();

        params.put("prompt", prompt);
        params.put("size", getSize());
        params.put("n", getN());
        params.put("response_format", getResponse_format());

        params.put("API", getAPI_KEY());
        params.put("url", getBaseUrl() + Config.getInstance().getProperties("url.openai.image_generator"));
        params.put("typeResponse", "image");

        return enquire(params);
    }
}
