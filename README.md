![JavaAI logo](https://github.com/artemnefedov/JavaAI/blob/resource/img/javaAi_logo.png?raw=true)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.artemnefedov/javaai.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.artemnefedov%22%20AND%20a:%22javaai%22)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/artemnefedov/JavaAI/blob/main/LICENSE)
## JavaAI is an open source library that allows you to interact with OpenAI models with just a few lines of code

#### Note:

> I wrote this library for myself since I couldn't find anything more suitable for my project, so I decided to share it
> with the community. I hope this library will help you.<br>
> Any help is welcome. I am always glad to hear new ideas and criticism.

## How to use it?

### Integration

>#### _Maven_
>
>```xml
><dependency>
>    <groupId>io.github.artemnefedov</groupId>
>    <artifactId>javaai</artifactId>
>    <version>0.3.5</version>
></dependency>
>```
> ___
>#### _Gradle_
>
>```groovy
>implementation 'io.github.artemnefedov:javaai:0.3.5'
>```

### Initialize JavaAI

> You can pass your API-Key in java code
>```java
>JavaAI javaAI = javaAiBuilder("YOUR_API-KEY");
>```
> ___
>
> Or paste it into the api-key field in the _**javaai.yaml**_, everyone loves yaml
>```yaml
>openai:
>   api-key: "YOUR_API-KEY"
>```
>```java
>JavaAI javaAI = javaAiBuilder();
>```

## Java AI example

### ChatGPT

>You have 2 options to use JavaAI to work with ChatGPT.
You can make a first request using List<ChatMessage> to set the context and get a response, and after that use a string.
<br>Both options retain the message history.
<br>The "**assistant**" role is used by default for answers, be careful.
>```java
>var messages = List.of(
>        new ChatMessage("user","Hello!"),
>        new ChatMessage("assistant","Hello! How can I assist you today?"));
>
>String chatResponse = javaAI.chat(messages);
>```
>#### _OR_
>```java
>javaAI.chat("What's 2 2?"); 
>javaAI.chat("What did I ask in the last question?");
>```
> > **user:** What's 2 2?<br>
> > **assistant:** 2 + 2 equals 4<br>
> > **user**: What did I ask in the last question?<br>
> > **assistant**: In your last question, you asked "What's 2 2?"<br>
---
### DALL·E 2
>Image generation, the model will return a URL to the result, as a List of String
>```java
>String imgUrl = javaAI.generateImage("cat sitting next to a cup of coffee");
>```
> ![cat_image](https://github.com/artemnefedov/JavaAI/blob/resource/img/cat_%20of_coffee.png?raw=true)
---
### Completions
>Text generation, the model will return the response as a String
>```java
>String response = javaAI.generateText("Say this is a test");
>```
---
**Notice:**

> You can always set your parameters for the models

Example for Chat:
> _In java code_
>```java
>javaAI.setChat(
>        Chat.builder()
>            .messages(new ArrayList<>())
>            .model("gpt-3.5-turbo")
>            .maxTokens(2000)
>            .n(1)
>            .build()
>        );
>```
> _or in **javaai.yaml**_
> ```yaml
> chat:
>   model: gpt-3.5-turbo
>   temperature: 0.9
>   top_p: 1
>   n: 1
>   stream: false
>   stop: \n
>   max_tokens: 2000
>   user:
> ```
---

## Models that JavaAI works with:

1. [x] [Completions](https://platform.openai.com/docs/api-reference/completions)
2. [x] [Chat-GPT](https://platform.openai.com/docs/api-reference/chat)
3. [x] [Create image](https://platform.openai.com/docs/api-reference/images/create)

---
## License

#### Distributed under the [MIT License](./LICENSE)
