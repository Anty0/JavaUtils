/*
 * javautils
 * Copyright (C)   2018  anty
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

package eu.codetopic.java.utils.collections

/**
 * @author anty
 */
interface Queue<T> {

    val size: Int

    fun isEmpty(): Boolean

    fun put(item: T): Boolean

    fun take(): T?

    fun peek(): T?
}

class QueueImpl<T>(items: Collection<T>): Queue<T> {

    constructor(vararg items: T) : this(items.asList())

    constructor() : this(emptyList())

    private val elements: MutableList<T> = items.toMutableList()

    override val size: Int get() = elements.size

    override fun isEmpty() = elements.isEmpty()

    override fun put(item: T) = elements.add(item)

    override fun take() = if (!isEmpty()) elements.removeAt(0) else null

    override fun peek() = if (!isEmpty()) elements[0] else null

    override fun toString(): String = elements.toString()
}

fun <T> Queue<T>.putAll(items: Collection<T>) = items.forEach { put(it) }

fun <T> Queue<T>.putAll(vararg items: T) = items.forEach { put(it) }

fun <T> queueOf(): Queue<T> = QueueImpl()

fun <T> queueOf(items: Collection<T>): Queue<T> = QueueImpl(items)

fun <T> queueOf(vararg items: T): Queue<T> = QueueImpl(items.asList())