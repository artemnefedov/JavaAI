package io.github.artemnefedov.javaai.service.impl;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * The type Config.
 */
public class Config {

    private static Map<String, Map<String, Object>> params;

    /**
     * Instantiates a new Config.
     */
    public Config() {
        params = getParams();
    }

    /**
     * Chat chat config.
     *
     * @return the chat config
     */
    static ChatConfig chat() {
        return new ChatConfig(params.get("chat"));
    }

    /**
     * The type Chat config.
     */
    static class ChatConfig {

        private static Map<String, Object> chatParams;

        /**
         * Instantiates a new Chat config.
         *
         * @param chParams the ch params
         */
        public ChatConfig(Map<String, Object> chParams) {
            chatParams = chParams;
        }

        /**
         * Model string.
         *
         * @return the string
         */
        public String model() {
            return (String) chatParams.get("model");
        }

        /**
         * Max tokens int.
         *
         * @return the int
         */
        public int maxTokens() {
            return (chatParams.get("max_tokens") != null) ? (int) chatParams.get("max_tokens") : 1500;
        }

        /**
         * N int.
         *
         * @return the int
         */
        public int n() {
            return (chatParams.get("n") != null) ? (int) chatParams.get("n") : 1;
        }

        /**
         * Temperature float.
         *
         * @return the float
         */
        public float temperature() {

            return (float) ((chatParams.get("temperature") != null) ? (double) chatParams.get("temperature") : 0.9);
        }

        /**
         * Top p byte.
         *
         * @return the byte
         */
        public byte topP(){
            return (byte) ((chatParams.get("top_p") != null) ? (int) chatParams.get("top_p") : 1);
        }

        /**
         * Stream boolean.
         *
         * @return the boolean
         */
        public boolean stream() {
            return (chatParams.get("stream") != null) ? (boolean) chatParams.get("stream") : false ;
        }

        /**
         * Stop string.
         *
         * @return the string
         */
        public String stop() {
            return (chatParams.get("stop") != null) ? (String) chatParams.get("stop") : null;
        }

        /**
         * User string.
         *
         * @return the string
         */
        public String user() {
            return (chatParams.get("user") != null) ? (String) chatParams.get("user") : null;
        }
    }

    private Map<String, Map<String, Object>> getParams() {

        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("javaai.yaml");

        return new Yaml().load(inputStream);
    }

    /**
     * Api key string.
     *
     * @return the string
     */
    String apiKey() {

        return (String) params.get("openai").get("api-key");
    }


    /**
     * Test key string.
     *
     * @return the string
     */
    public String testKey() {
        return (String) params.get("openapi").get("test-key");
    }

}
