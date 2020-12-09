import java.io.File

const val PREAMBLE_SIZE = 25

private fun List<Long>.isSubsetSum(i: Long): Boolean {
    return when {
        i == 0L -> true
        this.isEmpty() -> false
        this.last() > i -> this.take(this.size - 1).isSubsetSum(i)
        else -> this.dropLast(1).isSubsetSum(i) || this.dropLast(1).isSubsetSum(i - this.last())
    }
}

private fun <E> List<E>.findIndexed(action: (index: Int, E) -> Boolean): E? {
    this.forEachIndexed { index, e ->
        if (action(index, e)) {
            return e
        }
    }
    return null
}

private fun List<Long>.getConnectedSubList(invalidNumber: Long): List<Long>? {
    var l = 0
    var sum = 0L
    this.forEachIndexed { idx, value ->
        while (sum > invalidNumber && l < idx - 1) {
            sum -= this[l]
            l++
        }
        if (sum == invalidNumber) {
            return this.subList(l, idx)
        }
        sum += value
    }
    return null
}

private fun part1(numbers: List<Long>): Long? {
    val amble = numbers.drop(PREAMBLE_SIZE)

    return amble.findIndexed { index, i ->
        !numbers.subList(index, PREAMBLE_SIZE + index).isSubsetSum(i)
    }
}

private fun part2(numbers: List<Long>, invalidNumber: Long) {
    val subList = numbers.getConnectedSubList(invalidNumber)
    if (!subList.isNullOrEmpty()) {
        println(subList.min()!! + subList.max()!!)
    }
}

fun main() {
    val numbers = File("input_data/9").readLines().map { it.toLong() }

    val invalidNumber = part1(numbers)
    println(invalidNumber)
    part2(numbers, invalidNumber!!)
}
