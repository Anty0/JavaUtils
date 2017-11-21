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

package eu.codetopic.java.utils.access;

public class Acceding<E, T extends Access<E>> {

    private static final String LOG_TAG = "Acceding";

    private final T access;

    public Acceding(Accessible<E, T> accessible) {
        access = accessible.createAccess();
    }

    public T getAccess() {
        return access;
    }

    public synchronized final boolean isClosed() {
        return access.isClosed();
    }

    public synchronized final boolean close() {
        return access.close();
    }
}
