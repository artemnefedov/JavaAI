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

package io.github.artemnefedov.javaai.dto.errors;

import lombok.Data;

/**
 * dto to get errors from OpenAi API, everyone loves errors
 *
 * @author <a href="https://github.com/artemnefedov">Artyom Nefedov</a>.
 */
@Data
public class ErrorDetails {

    private Error error;

    public String getErrorDetails() {

        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Error details: ");
        errorMessage.append("\nmessage: ").append(this.error.getMessage());

        if (this.error.getType() != null) {
            errorMessage.append("\ntype: ").append(this.error.getType());
        }
        if (this.error.getParam() != null) {
            errorMessage.append("\nparam: ").append(this.error.getParam());
        }
        if (this.error.getCode() != 0) {
            errorMessage.append("\ncode: ").append(this.error.getCode());
        }

        return errorMessage.toString();
    }
}