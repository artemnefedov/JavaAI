package io.github.artemnefedov.javaai.dto;

public interface OpenAIModel {

    Class<? extends ModelResponse> getResponseModel();
}
