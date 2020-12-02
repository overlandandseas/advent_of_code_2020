import java.io.File
import java.lang.Integer.parseInt

fun main() {
    val stream = File("input_data/2")
    part1(stream)
    part2(stream)
}

fun hasCharSafeCheck(str: String, char: Char, index: Int): Boolean {
    return str.length >= index && str[index - 1] == char
}

fun part2(stream: File) {
    val result = stream.readLines().filter {
        val (positions, uniqueChar, password) = it.split(' ')

        val (first, second) = positions.split('-').map { num -> parseInt(num) }

        val char = uniqueChar[0]

        if (hasCharSafeCheck(password, char, first)) {
            !hasCharSafeCheck(password, char, second)
        } else {
            hasCharSafeCheck(password, char, second)
        }
    }.count()

    println(result)
}

fun part1(stream: File) {
    val result = stream.readLines().filter {
        val (minAndMax, uniqueChar, password) = it.split(' ')

        val (min, max) = minAndMax.split('-').map { num -> parseInt(num) }

        val numberOfTimes = password.filter { char -> char == uniqueChar[0] }.count()

        numberOfTimes in min..max
    }.count()

    println(result)
}
