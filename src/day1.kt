import java.io.File
import java.lang.Integer.parseInt

fun List<Int>.combination(size: Int): MutableList<List<Int>> {
    val arr = mutableListOf<List<Int>>()

    // Not sure how to make this repeat dynamically.
    this.forEach { i ->
        this.minus(0..i).forEach { j ->
            if (size == 2) {
                arr.add(arrayListOf(i, j))
            } else {
                this.minus(0..j).forEach { k ->
                    arr.add(arrayListOf(i, j, k))
                }
            }
        }
    }
    return arr
}

fun withCombination(size: Int, stream: File) {
    val result = stream.readLines()
        .map { parseInt(it) }
        .combination(size)
        .find { it.sum() == 2020 }
        ?.reduce { acc, i -> acc * i }

    println(result)
}

fun main() {
    val stream = File("input_data/1")
    withCombination(2, stream)
    withCombination(3, stream)
}

//
// OLD ANSWERS
//

// fun part1(stream: File) {
//
//    val numArray = stream.readLines().map { parseInt(it) }
//
//    numArray.forEach { a ->
//
//        val result = numArray.find { b ->
//            b + a == 2020
//        }
//        if (result != null) {
//            println(a * result)
//            return
//        }
//    }
// }
//
//
// fun part2(stream: File) {
//
//    val numArray = stream.readLines().map { parseInt(it) }
//
//    numArray.forEach { a ->
//
//        numArray.forEach { b ->
//            val result = numArray.find { c ->
//                a + b + c == 2020
//            }
//            if (result != null) {
//                println(a * b * result)
//                return
//            }
//        }
//
//    }
// }
