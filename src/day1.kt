import java.io.File
import java.lang.Integer.parseInt


fun main () {
    val stream = File("input_data/1")
    part1(stream)
    part2(stream)
}

fun part1(stream: File) {

    val numArray = stream.readLines().map { parseInt(it) }

    numArray.forEach { a ->

        val result = numArray.find { b ->
            b + a == 2020
        }
        if (result != null) {
            println(a * result)
            return
        }
    }
}


fun part2(stream: File) {

    val numArray = stream.readLines().map { parseInt(it) }

    numArray.forEach { a ->

        numArray.forEach { b ->
            val result = numArray.find { c ->
                a + b + c == 2020
            }
            if (result != null) {
                println(a * b * result)
                return
            }
        }

    }
}