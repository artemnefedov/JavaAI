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

/**
 * dto to send a request to the <a href="https://platform.openai.com/docs/api-reference/images/create">Create image</a>.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageBuilder implements OpenAIModel {

    private String prompt;
    private final int n = 1;
    private final String size = "1024x1024";
    @SerializedName("response_format")
    private final String responseFormat = "url";

    @Override
    public Class<? extends ModelResponse> getResponseModel() {
        return ImageModelResponse.class;
    }

    /**
     * The type Image model response.
     */
    public record ImageModelResponse(
            long created,
            List<ImageData> data

    ) implements ModelResponse {

        @Override
        public String getResponse() {

            return this.data.get(0).url();
        }

        /**
         * The type Image data.
         */
        public record ImageData(
                String url,
                String b64Json
        ) {
        }
    }
}
