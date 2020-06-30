package br.com.associacaoshare.utils

import java.time.ZoneId

private val compressRegex = Regex("\\s+")

val brazilZone = ZoneId.of("America/Sao_Paulo")

fun String.compressSpaces(): String {
    return replace(compressRegex, " ").trim()
}

fun String.limit(size: Int): String {
    return if (length <= size) this else substring(0, lastIndexOf(' ', size)).trim() + "..."
}

data class NeighbouringValue<out T>(val value: T, val before: T?, val after: T?)

fun <T> Iterable<T>.forEachNeighbouring(block: (NeighbouringValue<T>) -> Unit) {
    class Val(val it: T)

    var before: Val? = null
    var current: Val? = null
    var after: Val? = null
    val iterator = iterator()

    fun hasNext(): Boolean {
        val iterHasNext = iterator.hasNext()
        before = current
        current = after
        after = if (iterHasNext) Val(iterator.next()) else null

        return current != null || after != null
    }

    while (hasNext()) {
        current?.let {
            block(NeighbouringValue(it.it, before?.it, after?.it))
        }
    }
}