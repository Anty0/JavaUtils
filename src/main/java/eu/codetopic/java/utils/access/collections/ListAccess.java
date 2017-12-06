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

package eu.codetopic.java.utils.access.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import eu.codetopic.java.utils.access.Access;
import eu.codetopic.java.utils.access.Accessible;

public abstract class ListAccess<E> extends Access<List<E>> {

    private static final String LOG_TAG = "ListAccess";

    public ListAccess(Accessible<?, ?> creator, List<E> accessed) {
        super(creator, accessed);
    }

    public List<E> getImmutableContents() {
        return Collections.unmodifiableList(getAccessed());
    }

    public abstract void applyModifications(Object editTag, Collection<ListModification<E>> modifications);
}
