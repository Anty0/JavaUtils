/*
 * Copyright 2017 Jiří Kuchyňka (Anty)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.codetopic.java.utils.exception;

public class WrongIdException extends RuntimeException {

    public WrongIdException() {
        super();
    }

    public WrongIdException(String detailMessage) {
        super(detailMessage);
    }

    public WrongIdException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public WrongIdException(Throwable throwable) {
        super(throwable);
    }
}
