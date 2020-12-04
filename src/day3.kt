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

    val treeArr = arrayOf(0L, 0, 0, 0, 0)

    stream.readLines().forEachIndexed { index, s ->
        if (s[index % s.length] == TREE) treeArr[0]++ // 0: right 1, down 1
        if (s[index * 3 % s.length] == TREE) treeArr[1]++ // 1: right 3, down 1 (part 1)
        if (s[index * 5 % s.length] == TREE) treeArr[2]++ // 2: right 5, down 1
        if (s[index * 7 % s.length] == TREE) treeArr[3]++ // 3: right 7, down 1
        if (index % 2 == 0 && s[(index / 2) % s.length] == TREE) treeArr[4]++ // 4: right 1, down 2
    }

    println(treeArr.reduce { acc, l -> acc * l })

    treeArr.reduce(Long::times)
    treeArr.reduce { acc, l -> acc * l }
    treeArr.product()
}

private fun Array<Long>.product(): Long {
    return this.reduce(Long::times)
}
