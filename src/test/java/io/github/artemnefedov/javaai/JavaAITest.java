package io.github.artemnefedov.javaai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import io.github.artemnefedov.javaai.model.chat.ChatConfig;
import io.github.artemnefedov.javaai.model.chat.ChatMessage;
import io.github.artemnefedov.javaai.model.dalle.DalleConfig;
import io.github.artemnefedov.javaai.model.tts.TtsConfig;
import io.github.artemnefedov.javaai.service.Connection;
import io.github.artemnefedov.javaai.service.impl.JavaAIImpl;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JavaAITest {

    private Connection connectionMock;
    private JavaAIImpl javaAI;

    @BeforeEach
    void setUp() {
        connectionMock = Mockito.mock(Connection.class);
        javaAI = new JavaAIImpl("fakeApiKey");
        javaAI.setConnection(connectionMock);
    }

    @Test
    void generateImage_ReturnsExpectedUrl() {

        String expectedUrl = "Test url";

        JSONObject expectedUrlObject = new JSONObject();
        expectedUrlObject.put("url", expectedUrl);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(expectedUrlObject);

        Mockito.when(connectionMock.getJsonResponse(any()))
                .thenReturn(new JSONObject().put("data", jsonArray));

        String result = javaAI.generateImage("test prompt");
        assertEquals(expectedUrl, result);
    }

    @Test
    void generateImages_ReturnsExpectedUrls() {
        List<String> expectedUrls = List.of("URL 1", "URL 2");
        JSONArray jsonArray = new JSONArray();
        expectedUrls.forEach(url -> jsonArray.put(new JSONObject().put("url", url)));
        Mockito.when(connectionMock.getJsonResponse(any()))
                .thenReturn(new JSONObject().put("data", jsonArray));

        javaAI.setDalleConfig(new DalleConfig(
                DalleConfig.DalleModel.DALL_E_2,
                2,
                "standard",
                DalleConfig.ResponseFormat.URL,
                DalleConfig.Size.D1_D3_1024x1024,
                DalleConfig.Style.VIVID,
                "user"
        ));
        List<String> results = javaAI.generateImages("test prompt");
        assertEquals(expectedUrls, results);
    }

    @Test
    void textToSpeech_CreatesFileSuccessfully() {
        byte[] audioResponse = new byte[]{0, 1, 2, 3};
        Mockito.when(connectionMock.getAudioResponse(any())).thenReturn(audioResponse);

        boolean result = javaAI.textToSpeech("test prompt", "target/", "testFile");
        assertTrue(result);
    }

    @Test
    void chat_ReturnsExpectedResponse() {
        String expectedResponse = "Test response";

        JSONArray jsonArray = new JSONArray()
                .put(new JSONObject().put("message",
                        new JSONObject().put("content", "Test response")).put("index", 0));

        Mockito.when(connectionMock.getJsonResponse(any()))
                .thenReturn(new JSONObject().put("choices", jsonArray));

        String result = javaAI.chat(List.of(new ChatMessage("user", "Hello")));
        assertEquals(expectedResponse, result);
    }

    @Test
    void chatWithChoices_ReturnsExpectedResponses() {

        List<String> expectedMessages = List.of("Test response 1", "Test response 2",
                "Test response 3");

        JSONArray jsonArray = new JSONArray()
                .put(new JSONObject().put("message",
                        new JSONObject().put("content", "Test response 1")).put("index", 0))
                .put(new JSONObject().put("message",
                        new JSONObject().put("content", "Test response 2")).put("index", 1))
                .put(new JSONObject().put("message",
                        new JSONObject().put("content", "Test response 3")).put("index", 2));

        Mockito.when(connectionMock.getJsonResponse(any()))
                .thenReturn(new JSONObject().put("choices", jsonArray));

        javaAI.setChatConfig(new ChatConfig(
                ChatConfig.Model.GPT_4,
                0.5f,
                2,
                3,
                false,
                null,
                100,
                0.1f,
                0.1f,
                null,
                "user"
        ));

        List<String> results = javaAI.chatWithChoices(List.of(new ChatMessage("user", "Hello")));
        assertEquals(expectedMessages, results);
    }

    @Test
    void setChatConfig_AppliesConfigCorrectly() {
        ChatConfig chatConfig = new ChatConfig(ChatConfig.Model.GPT_4, 0.5f, 1, 5, false, null, 100,
                0.1f, 0.1f, null, "user");
        javaAI.setChatConfig(chatConfig);
        // Проверка, что конфигурация была применена, может включать проверку значений полей в объекте chat
        assertEquals(0.5f, javaAI.getChat().getConfig().temperature());
        assertEquals(5, javaAI.getChat().getConfig().n());
    }

    @Test
    void setDalleConfig_AppliesConfigCorrectly() {
        DalleConfig dalleConfig = new DalleConfig(DalleConfig.DalleModel.DALL_E_2, 3, "high",
                DalleConfig.ResponseFormat.URL, DalleConfig.Size.D1_512x512,
                DalleConfig.Style.VIVID, "user");
        javaAI.setDalleConfig(dalleConfig);
        // Проверка, что конфигурация была применена
        assertEquals(3, javaAI.getDalle().getConfig().n());
        assertEquals("high", javaAI.getDalle().getConfig().quality());
    }

    @Test
    void setTtsConfig_AppliesConfigCorrectly() {
        TtsConfig ttsConfig = new TtsConfig(TtsConfig.TtsModel.TTS_1, TtsConfig.Voice.ALLOY,
                TtsConfig.VoiceResponseFormat.MP3, 1.0f);
        javaAI.setTtsConfig(ttsConfig);
        // Проверка, что конфигурация была применена
        assertEquals(TtsConfig.Voice.ALLOY, javaAI.getTts().getConfig().voice());
        assertEquals(TtsConfig.VoiceResponseFormat.MP3,
                javaAI.getTts().getConfig().responseFormat());
    }
}