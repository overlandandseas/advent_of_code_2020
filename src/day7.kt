import java.io.File

fun main() {
    val stream = File("input_data/7")

    val rules = stream.readLines().map {
        val (color, contents) = it.split(" bags contain ")
        Pair(
            color,
            contents.split(", ").map { str ->
                val matchResults = "(\\d+) (\\w* \\w*)".toRegex().find(str)
                if (matchResults != null) {
                    Pair(matchResults.destructured.component1(), matchResults.destructured.component2())
                } else {
                    null
                }
            }
        )
    }.toMap()
    part1(rules)
    part2(rules)
}

private fun part2(rules: Map<String, List<Pair<String, String>?>>) {

    val answer = numberOfBags(rules, "shiny gold", 0)
    println(answer)
}
private fun part1(rules: Map<String, List<Pair<String, String>?>>) {

    val answer = checkForColor(rules, "shiny gold", mutableSetOf())
    println(answer.size)
}

private fun numberOfBags(rules: Map<String, List<Pair<String, String>?>>, color: String, numberOfBags: Int): Int {
    var bagNum = numberOfBags
    val contents = rules[color]

    contents!!.forEach { pair ->
        if (pair != null) {
            println("$color needs ${pair.first} ${pair.second} bag(s)")
            bagNum += pair.first.toInt()
            bagNum += numberOfBags(rules, pair.second, bagNum)
        }
    }
    return bagNum
}

private fun checkForColor(rules: Map<String, List<Pair<String, String>?>>, color: String, colors: MutableSet<String>): MutableSet<String> {

    rules.forEach { (key, value) ->
        if (hasColor(value, color)) {
            colors.add(key)
            checkForColor(rules, key, colors)
        }
    }
    return colors
}

private fun hasColor(contents: List<Pair<String, String>?>, color: String): Boolean {
    return contents.any {
        it?.second == color
    }
}
