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

package io.github.artemnefedov.javaai.model.tts;

import io.github.artemnefedov.javaai.model.Config;
import io.github.artemnefedov.javaai.model.OpenAiModel;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class Tts implements OpenAiModel {

    private final URL URL;
    private TtsConfig config;
    private String input;

    public Tts() {
        try {
            this.URL = new URL("https://api.openai.com/v1/audio/speech");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
        this.config = new TtsConfig(
                TtsConfig.TtsModel.TTS_1,
                TtsConfig.Voice.ALLOY,
                TtsConfig.VoiceResponseFormat.MP3,
                1.0f
        );
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public void setConfig(Config config) {
        this.config = (TtsConfig) config;
    }

    @Override
    public JSONObject getJson() {
        var params = this.config.getParamJson();
        params.put("input", this.input);
        return params;
    }

    @Override
    public String getResponse(JSONObject json) {
        return "";
    }

    public String getResponseFormat() {
        return this.config.responseFormat().getFormat();
    }

    @Override
    public URL getURL() {
        return this.URL;
    }
}
