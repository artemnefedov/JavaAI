# JavaAI

[![Maven Central](https://img.shields.io/maven-central/v/io.github.artemnefedov/javaai.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.artemnefedov%22%20AND%20a:%22javaai%22)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## The JavaAI Java SDK is a non-official open source library that allows Java developers to interact with OpenAI models with just a few lines of code.

#### Note:
> I wrote this library for myself because I couldn't find anything more convenient, so I decided to share my project with the community. I hope that I can help someone.
Any help is welcome, I am always open to new ideas and criticism.

## How to use it?


### 1. Import the library into your project.

_The package released to [Maven Central Repository](https://central.sonatype.com/artifact/io.github.artemnefedov/javaai/0.3.2)_

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
#### or download the JAR file from the [releases page](https://github.com/artemnefedov/JavaAI_OpenAI-SDK/releases)

### 2. Get the API key on the OpenAI website.
[openai.com/account/api-keys](https://platform.openai.com/account/api-keys)

### 3. Initialize OpenAI in the next project.
```java
OpenAI openAI = new OpenAIImplementation("YOUR_OPEN_AI_API-KEY");
```
### 4. Set the query parameters and get the result from the OpenAI model.

```java
//Text generation, the model will return the response as a String
String response = openAI.generateText("Say this is a test"))

//Image generation, the model will return a URL/ to the result, as a String
String imgURL = openAI.generateImage("A cute baby sea otter"));

//Text generation, taking into account the dialogue (GPT-3.5), the model will return the answer as a String
List<ChatMessage> messages = new ArrayList<>();

messages.add(new ChatMessage("user", "Hello!"));

String chatResponse = openAI.chat(messages);
```
#### Just in case, I added a [demo](https://github.com/artemnefedov/JavaAI/tree/demo).
<br>

**Notice:**
<br>
> You can always set your own parameters for models.

Example for ImageBuilder:
```java
ImageBuilder imageBuilder = new ImageBuilder( 1, "1024x1024", "url");
openAI.customImageBuilderConfig(imageBuilder);
```

---
## Models JavaAI can work with:
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
#### Distributed under the [MIT License](https://github.com/artemnefedov/OpenAI/blob/main/LICENSE)
# JavaAI