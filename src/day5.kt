import java.io.File

fun main() {
    val stream = File("input_data/5")

    part1(stream)
    part2(stream)
}

private fun part1(stream: File) {
    println(stream.readLines().map { getSeatId(it) }.max())
}

private fun part2(stream: File) {
    val listSorted = stream.readLines().map { getSeatId(it) }.sorted()
    var mySeat = listSorted[0] - 1
    listSorted.find {
        if (it - mySeat == 1) {
            mySeat = it
            false
        } else {
            mySeat++
            true
        }
    }
    println(mySeat)
}

private fun getSeatId(str: String): Int {

    return getRow(str) * 8 + getColumn(str)
}

private fun getRow(str: String): Int {
    return str.fold(Pair(0, 127)) { acc, char ->
        when (char) {
            'F' -> Pair(acc.first, (acc.second - acc.first).divide(2) + acc.first)
            'B' -> Pair((acc.second - acc.first).divide(2) + acc.first, acc.second)
            else -> acc
        }
    }.first
}

private fun getColumn(str: String): Int {
    return str.fold(Pair(0, 7)) { acc, char ->
        when (char) {
            'L' -> Pair(acc.first, (acc.second - acc.first).divide(2) + acc.first)
            'R' -> Pair((acc.second - acc.first).divide(2) + acc.first, acc.second)
            else -> acc
        }
    }.first
}

private fun Int.divide(divisor: Int): Int {
    return (this + (divisor / 2)) / divisor
}
