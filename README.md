# JavaAI

[![Maven Central](https://img.shields.io/maven-central/v/io.github.artemnefedov/javaai.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.artemnefedov%22%20AND%20a:%22javaai%22)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## JavaAI is an open source library that allows you to interact with OpenAI models with just a few lines of code

#### Note:
> I wrote this library for myself since I couldn't find anything more suitable for my project, so I decided to share it with the community. I hope this library will help you.<br>
Any help is welcome. I am always glad to hear new ideas and criticism.

## How to use it?

### Import the library

#### Maven:
```xml
<dependency>
    <groupId>io.github.artemnefedov</groupId>
    <artifactId>javaai</artifactId>
    <version>0.3.3</version>
</dependency>
```
#### Gradle:
```groovy
implementation group: 'io.github.artemnefedov', name: 'javaai', version: '0.3.3'
```

### Initialize OpenAI
```java
OpenAI openAI = new OpenAIImplementation("YOUR_OPEN_AI_API-KEY");
```
### Set the query parameters and get the result from the OpenAI model

```java
//Text generation, the model will return the response as a String
String response = openAI.generateText("Say this is a test"))

//Image generation, the model will return a URL/ to the result, as a List of String
List<String> imges = openAI.generateImage("A cute baby sea otter"));

//Text generation, taking into account the dialogue (GPT-3.5), the model will return the answer as a String
List<ChatMessage> messages = new ArrayList<>();

messages.add(new ChatMessage("user", "Hello!"));

String chatResponse = openAI.chat(messages);
```
#### Just in case, I added a [demo](https://github.com/artemnefedov/JavaAI/tree/demo)
<br>
---

**Notice:**
<br>
> You can always set your parameters for the models

Example for ImageBuilder:
```java
ImageBuilder imageBuilder = new ImageBuilder( 1, "1024x1024", "url");
openAI.customImageBuilderConfig(imageBuilder);
```

---
## Models that JavaAI works with:
1. [x] [Completions](https://platform.openai.com/docs/api-reference/completions)
2. [x] [Chat-GPT](https://platform.openai.com/docs/api-reference/chat)
3. [x] [Create image](https://platform.openai.com/docs/api-reference/images/create)

---
## Outside Dependencies.
#### This library uses:
* [Gson](https://github.com/google/gson), to convert JSON to Java objects.<br>
* [lombok](https://github.com/projectlombok/lombok) to make the code clearer.<br>
* [junit](https://github.com/junit-team/junit5) for testing.

## License
#### Distributed under the [MIT License](https://github.com/artemnefedov/JavaAI/blob/main/LICENSE)
