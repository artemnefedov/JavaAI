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

package io.github.artemnefedov.javaai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * dto to get errors from OpenAi API, everyone loves errors
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
@Getter
@Setter
@NoArgsConstructor
public class ErrorDetails {

    private Error error;

    /**
     * Generates a detailed error report
     *
     * @return error details as String
     */
    public String getErrorDetails() {

        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append("Error details: ");
        errorMessage.append("\n\t\tmessage: ").append(this.error.message());

        if (this.error.type() != null) {
            errorMessage.append("\n\t\ttype: ").append(this.error.type());
        }
        if (this.error.param() != null) {
            errorMessage.append("\n\t\tparam: ").append(this.error.param());
        }
        if (this.error.code() != null) {
            errorMessage.append("\n\t\tcode: ").append(this.error.code());
        }

        return errorMessage.toString();
    }

    /**
     * The type Error.
     */
    record Error(String message, String type, String param, String code) {
    }
}
