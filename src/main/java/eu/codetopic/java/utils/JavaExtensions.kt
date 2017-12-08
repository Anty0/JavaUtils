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
import java.io.*
import java.lang.Thread.sleep
import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * @author anty
 */
object JavaExtensions {

    private const val LOG_TAG = "JavaExtensions"

    //////////////////////////////////////
    //////REGION - TEXTS_AND_STRINGS//////
    //////////////////////////////////////

    @JvmStatic
    fun String.substring(start: String?, end: String?): String {
        return substring(
                start?.let { indexOf(it) }
                        ?.takeIf { it != -1 }
                        ?.let { it + start.length } ?: 0,
                end?.let { indexOf(it) }
                        ?.takeIf { it != -1 } ?: length
        )
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
}