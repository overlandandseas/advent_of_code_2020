import java.io.File

const val PREAMBLE_SIZE = 25

fun main() {
    val numbers = File("input_data/9").readLines().map { it.toLong() }

    day1(numbers)
}

private fun day1(numbers: List<Long>) {
    val amble = numbers.drop(PREAMBLE_SIZE)

    val result = amble.findIndexed { index, i ->
        !numbers.subList(index, PREAMBLE_SIZE + index).isSubsetSum(i)
    }
    println(result)
}

private fun List<Long>.isSubsetSum(i: Long): Boolean {
    return when {
        i == 0L -> true
        this.isEmpty() -> false
        this.last() > i -> this.take(this.size - 1).isSubsetSum(i)
        else -> this.dropLast(1).isSubsetSum(i) || this.dropLast(1).isSubsetSum(i - this.last())
    }
}

private fun <E> List<E>.findIndexed(function: (Int, E) -> Boolean): E? {
    this.forEachIndexed { index, e ->
        if (function(index, e)) {
            return e
        }
    }
    return null
}
