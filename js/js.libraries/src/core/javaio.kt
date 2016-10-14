package java.io

class IOException(message: String = "") : Exception() {}

interface Closeable {
    fun close(): Unit
}

interface Serializable
