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

package eu.codetopic.java.utils

import eu.codetopic.java.utils.exception.SourceUnavailableException
import eu.codetopic.java.utils.log.Log
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import java.io.*
import java.lang.ref.Reference
import kotlin.coroutines.experimental.SequenceBuilder

/**
 * @author anty
 */
object JavaExtensions {

    private const val LOG_TAG = "JavaExtensions"

    //////////////////////////////////////
    //////REGION - TEXTS_AND_STRINGS//////
    //////////////////////////////////////

    fun String.substring(start: String?, end: String?): String {
        val startIndex = start?.let { indexOf(it) }
                ?.alsoIf({ it == -1 }) {
                    throw IllegalArgumentException("Failed to find start string: \"$start\"")
                }
                ?.let { it + start.length } ?: 0
        val endIndex = end?.let { indexOf(it, startIndex) }
                ?.alsoIf({ it == -1 }) {
                    throw IllegalArgumentException("Failed to find end string: \"$end\"")
                } ?: length
        return substring(startIndex, endIndex)
    }

    fun String.substringOrNull(start: String?, end: String?): String? {
        val startIndex = start?.let { indexOf(it) }
                ?.alsoIf({ it == -1 }) { return null }
                ?.let { it + start.length } ?: 0
        val endIndex = end?.let { indexOf(it, startIndex) }
                ?.alsoIf({ it == -1 }) { return null }
                ?: length
        return substring(startIndex, endIndex)
    }

    @JvmStatic
    @JvmOverloads
    fun String.fillToLen(len: Int, anchor: Anchor, filler: Char = ' '): String {
        if (length >= len) return this

        val missingLen = len - length

        return when (anchor) {
            Anchor.LEFT -> this + (filler * missingLen)
            Anchor.RIGHT -> (filler * missingLen) + this
            Anchor.CENTER -> {
                val halfMissingLen = missingLen / 2F
                (filler * halfMissingLen.floor()) + this + (filler * halfMissingLen.ceil())
            }
        }
    }

    enum class Anchor {
        LEFT, RIGHT, CENTER
    }

    @JvmStatic
    fun String.addBeforeEveryLine(addition: String): String {
        return addition + replace("\n", "\n$addition")
    }

    @JvmStatic
    fun Throwable.printStackTraceToString(): String {
        return StringWriter().use {
            PrintWriter(it).use {
                printStackTrace(it)
                it.flush()
            }
            it.toString()
        }
    }

    //////////////////////////////////////
    //////REGION - NUMBERS////////////////
    //////////////////////////////////////

    @JvmStatic
    operator fun Char.times(other: Int): String {
        return StringBuilder().apply {
            repeat(other) { append(this@times) }
        }.toString()
    }

    @JvmStatic
    fun String.parseDouble(): Double {
        return java.lang.Double.parseDouble(replace(",", ".")
                .replace("[^\\d.]".toRegex(), ""))

    }

    @JvmStatic
    fun String.parseDoubleOrNull(): Double? =
            try { parseDouble() } catch (e: Exception) { null }

    @JvmStatic
    fun Float.floor(): Int = Math.floor(this.toDouble()).toInt()

    @JvmStatic
    fun Float.ceil(): Int = Math.ceil(this.toDouble()).toInt()

    @JvmStatic
    fun Double.format(digits: Int): String =
            java.lang.String.format("%.${digits}f", this)

    //////////////////////////////////////
    //////REGION - WEAK_REFERENCE/////////
    //////////////////////////////////////

    @JvmStatic
    fun <T> Reference<T>.getOrThrow(): T =
            get() ?: throw SourceUnavailableException("Referent was garbage collected")

    //////////////////////////////////////
    //////REGION - SERIALIZATION//////////
    //////////////////////////////////////

    @JvmStatic
    fun Serializable.serialize(): String {
        return ByteArrayOutputStream().apply {
            ObjectOutputStream(this@apply)
                    .use { it.writeObject(this@serialize) }
        }.toString()
    }

    @JvmStatic
    fun <T : Serializable> String.deserialize(): T? {
        return ObjectInputStream(ByteArrayInputStream(toByteArray())).use {
            try {
                @Suppress("UNCHECKED_CAST")
                it.readObject() as T
            } catch (e: Exception) {
                null
            }
        }
    }

    //////////////////////////////////////
    //////REGION - KOTLIN_SERIALIZATION///
    //////////////////////////////////////

    inline fun <reified T : Any> kSerializer(): KSerializer<T> = T::class.serializer()

    //////////////////////////////////////
    //////REGION - EXPRESSIONS////////////
    //////////////////////////////////////

    @JvmStatic
    fun <T> max(first: T, second: T, selector: (T) -> Int): T {
        return if (selector(first) >= selector(second)) first else second
    }

    //////////////////////////////////////
    //////REGION - COLLECTIONS////////////
    //////////////////////////////////////

    @JvmStatic
    inline fun <T1, T2, R> Array<out T1>.join(other: Array<out T2>, transform: (T1, T2) -> R): List<R> {
        val smaller = max(this, other) { -it.size }
        return smaller.indices.mapTo(ArrayList(smaller.size)) { transform(this[it], other[it]) }
    }

    //////////////////////////////////////
    //////REGION - THREADING//////////////
    //////////////////////////////////////

    @JvmStatic
    fun trySleep(time: Long): Boolean = try {
        Thread.sleep(time); true
    } catch (e: InterruptedException) {
        Log.d("Thread", "trySleep", e)
        Thread.currentThread().interrupt(); false
    }

    @JvmStatic
    fun trySleep(milis: Long, nanos: Int): Boolean = try {
        Thread.sleep(milis, nanos); true
    } catch (e: InterruptedException) {
        Log.d("Thread", "trySleep", e)
        Thread.currentThread().interrupt(); false
    }

    //////////////////////////////////////
    //////REGION - COLLECTIONS////////////
    //////////////////////////////////////

    inline fun <T, C : MutableIterable<T>> C.onEachIterate(
            action: (iterator: MutableIterator<T>, obj: T) -> Unit): C = apply {
        forEachIterate(action)
    }

    inline fun <T> MutableIterable<T>.forEachIterate(
            action: (iterator: MutableIterator<T>, obj: T) -> Unit) {
        val iterator = iterator()
        while (iterator.hasNext()) {
            action(iterator, iterator.next())
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    inline fun <K, V> Map.Entry<K, V>.asPair(): Pair<K, V> = key to value

    //////////////////////////////////////
    //////REGION - MISCELLANEOUS//////////
    //////////////////////////////////////

    inline fun <R> runIf(condition: () -> Boolean, block: () -> R): R? =
            if (condition()) block() else null

    inline fun <T, R> T.runIf(condition: T.() -> Boolean, block: T.() -> R): R? =
            if (condition()) block() else null

    inline fun <T> T.alsoIf(condition: (T) -> Boolean, block: (T) -> Unit): T {
        if (condition(this)) block(this)
        return this
    }

    inline fun <T> T.alsoIfNot(condition: (T) -> Boolean, block: (T) -> Unit): T {
        if (!condition(this)) block(this)
        return this
    }

    inline fun <T> T.alsoIfNull(block: () -> Unit): T {
        if (this == null) block()
        return this
    }

    inline fun Boolean?.alsoIfTrue(block: () -> Unit): Boolean? {
        if (this == true) block()
        return this
    }

    inline fun Boolean.alsoIfTrue(block: () -> Unit): Boolean {
        if (this) block()
        return this
    }

    inline fun Boolean?.alsoIfFalse(block: () -> Unit): Boolean? {
        if (this == false) block()
        return this
    }

    inline fun Boolean.alsoIfFalse(block: () -> Unit): Boolean {
        if (!this) block()
        return this
    }

    inline fun <T : R, R> T.letIf(condition: (T) -> Boolean, block: (T) -> R): R =
            if (condition(this)) block(this) else this

    inline fun <T : R, R> T?.letIfNull(block: () -> R): R = this ?: block()

    inline infix fun <T> T.IfNull(block: () -> Unit): T {
        if (this == null) block()
        return this
    }

    inline infix fun Boolean?.ifTrue(block: () -> Unit): Boolean? {
        if (this == true) block()
        return this
    }

    inline infix fun Boolean.ifTrue(block: () -> Unit): Boolean {
        if (this) block()
        return this
    }

    inline fun Boolean?.ifFalse(block: () -> Unit): Boolean? {
        if (this == false) block()
        return this
    }

    inline fun Boolean.ifFalse(block: () -> Unit): Boolean {
        if (!this) block()
        return this
    }

    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> T?.isNull(): Boolean = this == null

    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> T?.isNotNull(): Boolean = this != null

    inline fun <reified R> Any?.to(): R? = this as? R

    //////////////////////////////////////
    //////REGION - SequenceBuilder////////
    //////////////////////////////////////

    suspend inline fun <T> SequenceBuilder<T>.yield(block: () -> T) {
        yield(block())
    }

    suspend fun <T> SequenceBuilder<T>.yieldNotNull(value: T?) {
        if (value != null) yield(value)
    }

    suspend inline fun <T> SequenceBuilder<T>.yieldNotNull(block: () -> T?) {
        val value = block()
        if (value != null) yield(value)
    }

    //////////////////////////////////////
    //////REGION - Throwable//////////////
    //////////////////////////////////////

    @Suppress("NOTHING_TO_INLINE")
    inline fun Throwable.throwIt(): Nothing = throw this
}