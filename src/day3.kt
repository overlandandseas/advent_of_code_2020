import java.io.File

const val TREE = '#'

fun main() {
    val stream = File("input_data/3")

    part1(stream)
    part2(stream)
}

private fun part1(stream: File) {
    val result = stream.readLines().foldIndexed(0) { index, acc, s ->
        if (s[index * 3 % s.length] == TREE) acc + 1 else acc
    }

    println(result)
}

private fun part2(stream: File) {

    val result = stream.readLines().foldIndexed(arrayOf(0L, 0L, 0L, 0L, 0L)) { index, acc, s ->

        if (s[index % s.length] == TREE) acc[0]++ // 0: right 1, down 1
        if (s[index * 3 % s.length] == TREE) acc[1]++ // 1: right 3, down 1 (part 1)
        if (s[index * 5 % s.length] == TREE) acc[2]++ // 2: right 5, down 1
        if (s[index * 7 % s.length] == TREE) acc[3]++ // 3: right 7, down 1
        if (index % 2 == 0 && s[(index / 2) % s.length] == TREE) acc[4]++ // 4: right 1, down 2

        acc
    }.reduce { acc, i -> acc * i }

    println(result)
}
