![JavaAI logo](https://github.com/artemnefedov/JavaAI/blob/resource/img/javaAi_logo.png?raw=true)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.artemnefedov/javaai.svg?label=Maven%20Central&logo=apachemaven)](https://central.sonatype.com/artifact/io.github.artemnefedov/javaai/)
![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/artemnefedov/JavaAI/total)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/artemnefedov/JavaAI/blob/main/LICENSE)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/1194ce221f4f46ed950d4b05e6fd248c)](https://app.codacy.com/gh/artemnefedov/JavaAI/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)


## About
>JavaAI is a lightweight Java library with minimal third-party dependencies designed to interact with the OpenAI API. It provides an intuitive interface for accessing advanced AI capabilities in Java applications. With JavaAI, you can easily integrate state-of-the-art features into your projects, including chat with GPT, image generation in DALL-E, and text-to-speech with Whisper.


## Usage

### Prerequisites

- Java 21
- Maven or Gradle
- OpenAI API key

### Integration

#### Maven

```xml

<dependency>
    <groupId>io.github.artemnefedov</groupId>
    <artifactId>javaai</artifactId>
    <version>0.4.1</version>
</dependency>
```

#### Gradle

```groovy
implementation 'io.github.artemnefedov:javaai:0.4.1'
```

---

## Initialize JavaAI

>  You can initialize JavaAI in two ways: by directly passing the API key to the constructor or by adding environment variables with the key to your system, naming it **OPENAI_API_KEY** as recommended by [OpenAi](https://help.openai.com/en/articles/5112595-best-practices-for-api-key-safety#h_a1ab3ba7b2)

### Passing the API key directly to the constructor

```java
var javaAi = JavaAI.javaAiBuilder("YOUR_API_KEY");
```

### Using an environment variable

```java
var javaAI = JavaAI.javaAiBuilder();
```

---

## Example

### ChatGPT

> You can use two ways to interact with ChatGPT:
>1. Pass the user's message, as a string, to the `chat()` method.
>```java
> javaAi.chat("YOUR_QUESTION");
>```
>
>2. Pass a saved conversation to the method as a `List<ChatMessage>`.
>```java
> var messages = List.of(
>        new ChatMessage("user", "what is the meaning of life?"),
>        new ChatMessage("AI", "The meaning of life is to be happy."),
>        new ChatMessage("user", "are you sure?")
>);
>
>javaAI.chat(messages);
> ```
>
> Depending on the value of `n` you [set](#configuration), you can use either the `chat()` method, which returns
> a `String` response from the API, or the `chatWithChoices()` method, which returns multiple responses from the API
> as `List<String>`, depending on the value of `n` you set.
>
---

### DALL-E

> You can use the `generateImage()` method to generate an image from a text prompt. The model will return a URL to the
> result, as a List of String.
> ```java
> javaAI.generateImage("Computes science cat, photo on Fujifilm x100v, 2024");
> ```
> Response
> ![CS cat](https://github.com/artemnefedov/JavaAI/blob/resource/img/cs-cat.jpg?raw=true)


---

### TTS

> To translate text to speech, you must pass to the `textToSpeech()` method a `string` containing the text you want to
> voice and a `string` containing the location where the audio file will be saved.
> ```java
> javaAI.textToSpeech("Hi, my name is Artem, and I made this piece of... code.", "path/to/save/audio.mp3");
>```
>Response
>
> https://github.com/artemnefedov/JavaAI/assets/74130706/82d315be-def0-4946-b560-ab0772f64051


---

## Configuration

You can specify different settings for each model, via the `setChatConfig()`, `setDalleConfig()`, and `setTtsConfig()` methods. You are accepting records `ChatConfig`, `DalleConfig`, and `TtsConfig` respectively.

### Config records view

---
`ChatConfig.java`
```java
public record ChatConfig(
        Model model,
        float temperature,
        int topP,
        int n,
        boolean stream,
        String stop,
        int maxTokens,
        float presencePenalty,
        float frequencyPenalty,
        Map<Integer, Integer> logitBias,
        String user) {
}
```

Parameters in [OpenAI API docs](https://platform.openai.com/docs/api-reference/chat/create)

---
`DalleConfig.java`

```java
public record DalleConfig(
        DalleModel model,
        int n,
        String quality,
        ResponseFormat responseFormat,
        Size size,
        Style style,
        String user) {
}
```

Parameters in [OpenAI API docs](https://platform.openai.com/docs/api-reference/images)

---
`TtsConfig.java`

```java
public record TtsConfig(
        TtsModel model,
        Voice voice,
        VoiceResponseFormat responseFormat,
        float speed
) {
}
```

Parameters in [OpenAI API docs](https://platform.openai.com/docs/api-reference/audio/createSpeech)

---

### Example for Chat:

```java
import io.github.artemnefedov.javaai.model.chat.ChatConfig;

var customChatConfig = new ChatConfig(
        ChatConfig.Model.GPT_3_5_TURBO,
        1F,
        1,
        1,
        false,
        "\n",
        2000,
        0F,
        0F,
        new HashMap<>(),
        UUID.randomUUID().toString()
);

javaAi.setChatConfig(customChatConfig);
```

---

## Features

- [x]  [GPT](https://platform.openai.com/docs/models/gpt-4-and-gpt-4-turbo)

- [x]  [DALL·E](https://platform.openai.com/docs/models/dall-e)

- [x]  [TTS](https://platform.openai.com/docs/models/tts)

- [ ]  [Whisper](https://platform.openai.com/docs/models/whisper)

---

## License

#### Distributed under the [MIT License](https://raw.githubusercontent.com/artemnefedov/JavaAI/main/LICENSE)
