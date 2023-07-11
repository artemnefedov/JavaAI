package io.github.artemnefedov.javaai.service.impl;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Config {

    private final Map<String, Object> params;

    public Config() {

        this.params = getParams();
    }

    private Map<String, Object> getParams() {

        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("javaai.yaml");

        return new Yaml().load(inputStream);
    }

    String apiKey() {
        return (String) params.get("api-key");
    }


    public String testKey() {
        return (String) params.get("test-key");
    }

}
