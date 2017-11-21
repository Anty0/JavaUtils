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

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

import eu.codetopic.java.utils.JavaUtils;

public class WeakAccess<A> extends Access<WeakReference<A>> {

    private static final String LOG_TAG = "WeakAccess";

    public WeakAccess(Accessible<?, ?> creator, @NotNull A accessed) {
        super(creator, new WeakReference<>(accessed));
    }

    @NotNull
    protected synchronized A getExtractedAccessed() {
        return JavaUtils.extractReference(getAccessed());
    }
}
