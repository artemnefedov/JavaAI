![JavaAI logo](https://github.com/artemnefedov/JavaAI/blob/resource/img/javaAi_logo.png?raw=true)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.artemnefedov/javaai.svg?label=Maven%20Central&logo=apachemaven)](https://central.sonatype.com/artifact/io.github.artemnefedov/javaai/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/artemnefedov/JavaAI/blob/main/LICENSE)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/1194ce221f4f46ed950d4b05e6fd248c)](https://app.codacy.com/gh/artemnefedov/JavaAI/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)




## Is a lightweight and easy to use library that allows you to interact with OpenAI models with just a few lines of code

## How to use?

### Integration

#### _Maven_

```xml
<dependency>
    <groupId>io.github.artemnefedov</groupId>
    <artifactId>javaai</artifactId>
    <version>0.4.0</version>
</dependency>
```
 ___
#### _Gradle_

```groovy
implementation 'io.github.artemnefedov:javaai:0.4.0'
```

### Initialize JavaAI

```java
   JavaAI javaAI = javaAiBuilder("YOUR_API-KEY");
```

## Java AI example

### ChatGPT

You have 2 options to use JavaAI to work with ChatGPT.
You can make a first request using List<ChatMessage> to set the context and get a response, and after that use a string.
<br>Both options retain the message history.
<br>The "**assistant**" role is used by default for answers, be careful.
```java
var messages = List.of(
        new ChatMessage("user","Hello!"),
        new ChatMessage("assistant","Hello! How can I assist you today?"));

String chatResponse = javaAI.chat(messages);
```
#### _OR_
```java
javaAI.chat("What's 2 2?"); 
javaAI.chat("What did I ask in the last question?");
```
> ### Example of communication
> **user:** What's 2 2?<br>
> **assistant:** 2 + 2 equals 4<br>
> **user**: What did I ask in the last question?<br>
> **assistant**: In your last question, you asked "What's 2 2?"<br>
---
### DALL·E 2
Image generation, the model will return a URL to the result, as a List of String
```java
String imgUrl = javaAI.generateImage("cat sitting next to a cup of coffee");
```
 ![cat_image](https://github.com/artemnefedov/JavaAI/blob/resource/img/cat_%20of_coffee.png?raw=true)
---
### Completions
Text generation, the model will return the response as a String
```java
String response = javaAI.generateText("Say this is a test");
```
---

### You can always set your parameters for the models

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
## License

#### Distributed under the [MIT License](./LICENSE)