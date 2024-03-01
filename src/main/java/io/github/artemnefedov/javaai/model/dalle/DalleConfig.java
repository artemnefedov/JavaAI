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

package io.github.artemnefedov.javaai.model.dalle;

import io.github.artemnefedov.javaai.model.Config;
import org.json.JSONObject;

import java.util.Map;

public record DalleConfig(
        DalleModel model,
        int n,
        String quality,
        ResponseFormat responseFormat,
        Size size,
        Style style,
        String user) implements Config {

    @Override
    public JSONObject getParamJson() {
        return new JSONObject(
                Map.of(
                        "model", this.model().getModel(),
                        "n", this.n(),
                        "quality", this.quality(),
                        "response_format", this.responseFormat().getFormat(),
                        "size", this.size().getSize(),
                        "style", this.style().getStyle(),
                        "user", this.user()
                )
        );
    }

    public enum DalleModel {
        DALL_E_2("dall-e-2"),
        DALL_E_3("dall-e-3");

        private final String model;

        DalleModel(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }
    }

    public enum ResponseFormat {
        URL("url"),
        B63_JSON("b64_json");

        private final String format;

        ResponseFormat(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public enum Size {
        D1_256x256("256x256"),
        D1_512x512("512x512"),
        D1_D3_1024x1024("1024x1024"),
        D2_1792x1024("1792x1024"),
        D2_1024x1792("1024x1792");

        private final String size;

        Size(String size) {
            this.size = size;
        }

        public String getSize() {
            return size;
        }

    }

    public enum Style {
        VIVID("vivid"),
        NATURAL("natural");

        private final String style;

        Style(String style) {
            this.style = style;
        }

        public String getStyle() {
            return style;
        }
    }

}
