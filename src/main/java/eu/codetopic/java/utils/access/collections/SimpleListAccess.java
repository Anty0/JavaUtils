/*
 * ApplicationPurkynka
 * Copyright (C)  2017  anty
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.codetopic.java.utils.access.collections;

import java.util.Collection;
import java.util.List;

import eu.codetopic.java.utils.access.Accessible;

public class SimpleListAccess<E> extends ListAccess<E> {

    private static final String LOG_TAG = "SimpleListAccess";

    public SimpleListAccess(Accessible<?, ?> creator, List<E> accessed) {
        super(creator, accessed);
    }

    @Override
    public void applyModifications(Object editTag, Collection<ListModification<E>> listModifications) {
        List<E> toModify = getAccessed();
        for (ListModification<E> modification : listModifications) {
            modification.modify(toModify);
        }
    }
}
