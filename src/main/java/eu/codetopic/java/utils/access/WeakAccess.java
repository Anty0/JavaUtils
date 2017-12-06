/*
 * javautils
 * Copyright (C)   2017  anty
 *
 * This program is free  software: you can redistribute it and/or modify
 * it under the terms  of the GNU General Public License as published by
 * the Free Software  Foundation, either version 3 of the License, or
 * (at your option) any  later version.
 *
 * This program is distributed in the hope that it  will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied  warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.   See the
 * GNU General Public License for more details.
 *
 * You  should have received a copy of the GNU General Public License
 * along  with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.codetopic.java.utils.access;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

import eu.codetopic.java.utils.JavaExtensions;

public class WeakAccess<A> extends Access<WeakReference<A>> {

    private static final String LOG_TAG = "WeakAccess";

    public WeakAccess(Accessible<?, ?> creator, @NotNull A accessed) {
        super(creator, new WeakReference<>(accessed));
    }

    @NotNull
    protected synchronized A getExtractedAccessed() {
        return JavaExtensions.getOrThrow(getAccessed());
    }
}
