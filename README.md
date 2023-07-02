# JavaAI

[![Maven Central](https://img.shields.io/maven-central/v/io.github.artemnefedov/javaai.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.artemnefedov%22%20AND%20a:%22javaai%22)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## JavaAI is an open source library that allows you to interact with OpenAI models with just a few lines of code

#### Note:

> I wrote this library for myself since I couldn't find anything more suitable for my project, so I decided to share it
> with the community. I hope this library will help you.<br>
> Any help is welcome. I am always glad to hear new ideas and criticism.

## How to use it?

### Import the library

#### Maven:

```xml

<dependency>
    <groupId>io.github.artemnefedov</groupId>
    <artifactId>javaai</artifactId>
    <version>0.3.4</version>
</dependency>
```

#### Gradle:

```groovy
implementation group: 'io.github.artemnefedov', name: 'javaai', version: '0.3.4'
```

### Initialize JavaAI

```java
JavaAI javaAI=javaAiBuilder("YOUR_API-KEY");
```

## Java AI example

### ChatGPT

>You have 2 options to use JavaAI to work with ChatGPT.
You can make a first request using List<ChatMessage> to set the context and get a response, and after that use a string.
<br>Both options retain the message history.
<br>The "**assistant**" role is used by default for answers, be careful.

```java
var messages=List.of(
        new ChatMessage("user","Hello!"),
        new ChatMessage("assistant","Hello! How can I assist you today?"));

String chatResponse = javaAI.chat(messages);
```
#### _OR_
```java
javaAI.chat("What's 2 2?"); // answer: 2 + 2 equals 4.
javaAI.chat("What did I ask in the last question?"); //answer: In your last question, you asked "What's 2 2?"
```

### Completions
```java
//Text generation, the model will return the response as a String
String response = javaAI.generateText("Say this is a test");
```

### DALL·E 2
```java
//Image generation, the model will return a URL/ to the result, as a List of String
String imgUrl=javaAI.generateImage("A cute baby sea otter");
```

---

**Notice:**

> You can always set your parameters for the models

Example for Chat:

```java
javaAI.setChat(
        Chat.builder()
            .messages(new ArrayList<>())
            .model("gpt-3.5-turbo")
            .maxTokens(2000)
            .n(1)
            .build()
        );
```

---

## Models that JavaAI works with:

1. [x] [Completions](https://platform.openai.com/docs/api-reference/completions)
2. [x] [Chat-GPT](https://platform.openai.com/docs/api-reference/chat)
3. [x] [Create image](https://platform.openai.com/docs/api-reference/images/create)

---

## Outside Dependencies.

* [Gson](https://github.com/google/gson)
* [lombok](https://github.com/projectlombok/lombok)
* [junit](https://github.com/junit-team/junit5)
* [logback-classic](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic)

## License

#### Distributed under the [MIT License](https://github.com/artemnefedov/JavaAI/blob/main/LICENSE)
