# JavaAI is an open-source Java library for interacting with the OpenAI API

[//]: # ([![Maven Central]&#40;https://img.shields.io/maven-central/v/io.github.artemnefedov/openaisdk.svg?label=Maven%20Central&#41;]&#40;https://search.maven.org/search?q=g:%22io.github.artemnefedov%22%20AND%20a:%22openaisdk%22&#41; )
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## The JavaAI Java SDK is a non-official open source library that allows Java developers to interact with OpenAI models with just a few lines of code.

#### Note:
> I wrote this library for myself because I couldn't find anything more convenient, so I decided to share my project with the community. I hope that I can help someone.
Any help is welcome, I am always open to new ideas and criticism.

## How to use it?


### 1. Import the library into your project.

_The package released to [Maven Central Repository](https://central.sonatype.com/artifact/io.github.artemnefedov/javaai/0.2.3/)_

#### Maven:
```xml
<dependency>
    <groupId>io.github.artemnefedov</groupId>
    <artifactId>javaai</artifactId>
    <version>0.2.3</version>
</dependency>
```
#### Gradle:
```groovy
implementation group: 'io.github.artemnefedov', name: 'javaai', version: '0.2.3'
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
## Models JavaAI can work with:
### Language models
- [x] [Completions](https://platform.openai.com/docs/api-reference/completions)
- [x] [Edits](https://platform.openai.com/docs/api-reference/edits)
- [ ] [Chat](https://platform.openai.com/docs/api-reference/chat)

### Image models
- [x] [Create image](https://platform.openai.com/docs/api-reference/images/create)
---
## Outside Dependencies.
#### This library uses [Gson](https://github.com/google/gson), to convert JSON to Java objects.

## License
#### Distributed under the [MIT License](https://github.com/artemnefedov/OpenAI/blob/main/LICENSE)
# JavaAI
