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
import org.json.JSONObject;

public record TtsConfig(
        TtsModel model,
        Voice voice,
        VoiceResponseFormat responseFormat,
        float speed
) implements Config {

    @Override
    public JSONObject getParamJson() {
        var param = new JSONObject();
        param.put("model", this.model().getModel());
        param.put("voice", this.voice().getVoice());
        param.put("response_format", this.responseFormat().getFormat());
        param.put("speed", this.speed());
        return param;
    }

    public enum TtsModel {
        TTS_1("tts-1"),
        TTS_1_HD("tts-1-hd");

        private final String model;

        TtsModel(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }
    }

    public enum Voice {
        ALLOY("alloy"),
        ECHO("echo"),
        FABLE("fable"),
        ONYX("onyx"),
        NOVA("nova"),
        SHIMMER("shimmer");

        private final String voice;

        Voice(String voice) {
            this.voice = voice;
        }

        public String getVoice() {
            return voice;
        }
    }

    public enum VoiceResponseFormat {
        MP3("mp3"),
        OPUS("opus"),
        AAC("aac"),
        FLAC("flac"),
        WAV("wav"),
        PCM("pcm");

        private final String format;

        VoiceResponseFormat(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}
