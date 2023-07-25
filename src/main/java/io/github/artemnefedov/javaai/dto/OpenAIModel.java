package io.github.artemnefedov.javaai.dto;

/**
 * The interface OpenAi model.
 */
public interface OpenAIModel {

    /**
     * Gets response model.
     *
     * @return the response model
     */
    Class<? extends ModelResponse> getResponseModel();
}
