package io.github.artemnefedov.javaai.service.impl;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Config {

    private static Map<String, Map<String, Object>> params;

    public Config() {
        params = getParams();
    }

    static ChatConfig chat() {
        return new ChatConfig(params.get("chat"));
    }

    static class ChatConfig {

        private static Map<String, Object> chatParams;

        public ChatConfig(Map<String, Object> chParams) {
            chatParams = chParams;
        }

        public String model() {
            return (String) chatParams.get("model");
        }

        public int maxTokens() {
            return (chatParams.get("max_tokens") != null) ? (int) chatParams.get("max_tokens") : 1500;
        }

        public int n() {
            return (chatParams.get("n") != null) ? (int) chatParams.get("n") : 1;
        }

        public float temperature() {

            return (float) ((chatParams.get("temperature") != null) ? (double) chatParams.get("temperature") : 0.9);
        }

        public byte topP(){
            return (byte) ((chatParams.get("top_p") != null) ? (int) chatParams.get("top_p") : 1);
        }

        public boolean stream() {
            return (chatParams.get("stream") != null) ? (boolean) chatParams.get("stream") : false ;
        }

        public String stop() {
            return (chatParams.get("stop") != null) ? (String) chatParams.get("stop") : null;
        }

        public String user() {
            return (chatParams.get("user") != null) ? (String) chatParams.get("user") : null;
        }
    }

    private Map<String, Map<String, Object>> getParams() {

        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("javaai.yaml");

        return new Yaml().load(inputStream);
    }

    String apiKey() {

        return (String) params.get("openai").get("api-key");
    }



    public String testKey() {
        return (String) params.get("openapi").get("test-key");
    }

}
