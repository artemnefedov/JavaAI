/*
 *  MIT License
 *
 *  Copyright (c)  2023. Artyom Nefedov
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package io.github.artemnefedov.javaai.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config is a class that reads the configuration properties from
 * a "config.properties" file.
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
public class Config {

    private static Config instance;

    private final Properties properties;

    /**
     * Private constructor to prevent multiple instances from being created.
     */
    private Config() {
        properties = new Properties();
        try (InputStream config = getClass().getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(config);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get the singleton instance of the {@link Config} class, if the instance
     * does not exist, it will be created.
     *
     * @return the instance of Config.
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * The getProperties() method interacts with the config.properties file.
     *
     * @param key key name
     * @return the value of the key
     */
    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}