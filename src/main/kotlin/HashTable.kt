
import java.util.*

class HashTable<T> {
    private class Bucket<T> {
        var data = LinkedList<T>()
        fun add(value: T): Boolean {
            return if (data.contains(value)) false else data.add(value)
        }

        operator fun contains(value: T): Boolean {
            return data.contains(value)
        }
    }

    private var buckets: Array<Bucket<T>>
    private var size: Int
    private val loadFactor: Double

    operator fun contains(value: T): Boolean {
        return Arrays.stream(buckets).anyMatch { b: Bucket<T> ->
            b.contains(
                value
            )
        }
    }

    fun add(value: T): Boolean {
        if (contains(value)) return false
        ensureCapacity(size + 1)
        val pos = value.hashCode() % buckets.size
        size++
        return buckets[pos].add(value)
    }

    operator fun plus(value: T) : HashTable<T> {
        this.add(value)
        return this
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (bucket in buckets) {
            for (t in bucket.data) {
                sb.append(t).append(", ")
            }
        }
        sb.delete(sb.length - 2, sb.length)
        return "[$sb]"
    }

    private fun ensureCapacity(newSize: Int) {
        if (newSize * 1.0 / buckets.size > loadFactor) {
            doubleBuckets()
        }
    }

    private fun doubleBuckets() {
        val newArr = Array<Bucket<T>>(buckets.size * 2) { Bucket() }
        for (bucket in buckets) {
            for (t in bucket.data) {
                val p = t.hashCode() % newArr.size
                newArr[p].data.add(t)
            }
        }
        buckets = newArr
    }

    init {
        buckets = Array(8) { Bucket() }
        size = 0
        loadFactor = 0.75
    }
}