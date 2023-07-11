package io.github.artemnefedov.javaai.dto;

/**
 * The interface Open ai model.
 */
public interface OpenAIModel {

    /**
     * Gets response model.
     *
     * @return the response model
     */
    Class<? extends ModelResponse> getResponseModel();
}
