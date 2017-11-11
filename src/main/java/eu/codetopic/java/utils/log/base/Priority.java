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

package eu.codetopic.java.utils.log.base;

public enum Priority {

    VERBOSE('V'), DEBUG('D'), INFO('I'), WARN('W'), ERROR('E'), ASSERT('A');

    private final char displayID;

    Priority(char displayID) {
        this.displayID = displayID;
    }

    public char getDisplayID() {
        return displayID;
    }
}
