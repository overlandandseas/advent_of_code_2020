import java.io.File

fun main() {
    val stream = File("input_data/8")

    val instructions = stream.readLines().map {
        val (instruction, amount) = it.split(' ')
        Pair(instruction, amount.toInt())
    }

    part1(instructions)
    part2(instructions)
}

private fun part1(instructions: List<Pair<String, Int>>) {

    var i = 0
    var acc = 0
    val instructionSet = mutableSetOf<Int>()
    while (i < instructions.size && !instructionSet.contains(i)) {
        val (inst, amount) = instructions[i]

        when (inst) {
            "acc" -> acc += amount
            "jmp" -> i += (amount - 1)
        }
        instructionSet.add(i)
//        println("$inst $amount\t|$acc")
        i++
    }

    println(acc)
}

private fun part2(instructions: List<Pair<String, Int>>) {

    var instructionsToUse = instructions
    var i = 0
    var acc = 0
    val instructionSet = mutableSetOf<Int>()
    var setIndex = 0
    while (i < instructions.size) {

        if (instructionSet.contains(i)) {
            i = 0
            acc = 0
            instructionSet.clear()
            instructionsToUse = changeOneInstruction(instructions, setIndex)
            setIndex++
        }

        val (inst, amount) = instructionsToUse[i]
        when (inst) {
            "acc" -> acc += amount
            "jmp" -> i += (amount - 1)
        }

        instructionSet.add(i)
        i++
    }

    println(acc)
}

private fun changeOneInstruction(instructions: List<Pair<String, Int>>, index: Int): List<Pair<String, Int>> {
    var found = false
    return instructions.mapIndexed { idx, pair ->
        if (idx >= index && !found) {
            when (pair.first) {
                "jmp" -> {
                    found = true
                    Pair("nop", pair.second)
                }
                "nop" -> {
                    found = true
                    Pair("jump", pair.second)
                }
                else -> pair
            }
        } else {
            pair
        }
    }
}
