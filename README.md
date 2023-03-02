# JavaAI is an open-source Java library for interacting with the OpenAI API
[![Maven Central](https://img.shields.io/maven-central/v/io.github.artemnefedov/openaisdk.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.artemnefedov%22%20AND%20a:%22openaisdk%22) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## The JavaAI Java SDK is a non-official open source library that allows Java developers to interact with OpenAI models with just a few lines of code.

#### Note:
> I wrote this library for myself because I couldn't find anything more convenient, so I decided to share my project with the community. I hope that I can help someone.
Any help is welcome, I am always open to new ideas and criticism.

## How to use it?


### 1. Import the library into your project.
#### Maven:
```xml
<dependency>
    <groupId>io.github.artemnefedov</groupId>
    <artifactId>openaisdk</artifactId>
    <version>0.1.1</version>
</dependency>
```
#### Gradle:
```groovy
implementation 'io.github.artemnefedov:openaisdk:0.1.1'
```
#### or download the JAR file from the [releases page](https://github.com/artemnefedov/OpenAI/releases)

### 2. Get the API key on the OpenAI website.
[openai.com/account/api-keys](https://platform.openai.com/account/api-keys)

### 3. Initialize the language model in your project.
```java
LanguageModel completions = new LanguageModel("YOUR_OPEN_AI_API-KEY");
```
#### Check out the [Wiki page](https://github.com/artemnefedov/OpenAI/wiki/Initialize-the-language-model-in-your-project) for other ways

### 4. Set the query parameters and get the result from the OpenAI model.

```java
completions.setPrompt("What is the first line programmers print?")

String result = completions.generateText();
```
**Conclusion:** `The first line a programmer typically prints is "Hello World!"` 

---
## Only [GTP-3](https://platform.openai.com/docs/models/gpt-3) is supported at the moment, already working on making it work with DALL-E 2.

---
## Outside Dependencies.
#### This library uses [Gson](https://github.com/google/gson), to convert JSON to Java objects.

## License
#### Distributed under the [MIT License](https://github.com/artemnefedov/OpenAI/blob/main/LICENSE)
# JavaAI
