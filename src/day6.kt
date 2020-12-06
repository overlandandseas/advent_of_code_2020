import java.io.File

fun main() {
    val stream = File("input_data/6")

    part1(stream)
    part2(stream)
}

private fun part1(stream: File) {

    val result = stream.readText().split("\n\n").map {
        it.removeWs().toSet().size
    }.sum()
    println(result)
}

private fun String.removeWs(): String {
    return this.replace("\\s+".toRegex(), "")
}

private fun part2(stream: File) {

    val result = stream.readText().split("\n\n").map {
        val size = it.split("\n").size
        it.removeWs().fold(mutableMapOf<Char, Int>()) { acc, c ->
            acc[c] = acc.getOrDefault(c, 0) + 1
            acc
        }.filter { (_, value) ->
            value == size
        }.size
    }.sum()
    println(result)
}
