import java.io.File

val VALID_FIELD_NAMES = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

val EYE_COLORS = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

fun main() {
    val stream = File("input_data/4")
    val passports = stream.readText().split("\n\n").map { it.split("\\s".toRegex()) }.map {

        val passport = HashMap<String, String>()
        it.forEach { record ->
            if (record.contains(":")) {
                val (key, value) = record.split(':')
                passport[key] = value
            }
        }
        passport
    }
    part1(passports)
    part2(passports)
}

private fun part2(passports: List<HashMap<String, String>>) {
    println(passports.count { isValidPassport2(it) })
}

private fun part1(passports: List<HashMap<String, String>>) {
    println(passports.count { hasRequiredFields(it) })
}

private fun isValidPassport2(passport: Map<String, String>): Boolean {
    return hasRequiredFields(passport) && passport.all { (key, value) ->
        when (key) {
            "byr" -> value.toInt() in 1920..2002
            "iyr" -> value.toInt() in 2010..2020
            "eyr" -> value.toInt() in 2020..2030
            "hgt" -> when {
                value.contains("cm") -> {
                    value.split("cm")[0].toInt() in 150..193
                }
                value.contains("in") -> {
                    value.split("in")[0].toInt() in 59..76
                }
                else -> false
            }
            "hcl" -> value.matches("#[a-f0-9]{6}".toRegex())
            "ecl" -> EYE_COLORS.contains(value)
            "pid" -> value.matches("[0-9]{9}".toRegex())
            else -> true
        }
    }
}




//
// Longer Version
//
@Suppress("UNUSED_FUNCTION")
private fun isValidPassport(passport: Map<String, String>): Boolean {
    return if (hasRequiredFields(passport)) {
        passport.all {
            when (it.key) {
                "byr" -> checkYear(it.value, 1920, 2002)
                "iyr" -> checkYear(it.value, 2010, 2020)
                "eyr" -> checkYear(it.value, 2020, 2030)
                "hgt" -> checkHeight(it.value)
                "hcl" -> checkHairColor(it.value)
                "ecl" -> checkEyeColor(it.value)
                "pid" -> checkPassportID(it.value)
                else -> true
            }
        }
    } else false
}

private fun hasRequiredFields(passport: Map<String, String>): Boolean {
    return VALID_FIELD_NAMES.all {
        passport.containsKey(it)
    }
}

private fun checkPassportID(str: String): Boolean {
    return str.matches("[0-9]{9}".toRegex())
}

private fun checkHairColor(str: String): Boolean {
    return str.matches("#[a-f0-9]{6}".toRegex())
}

private fun checkEyeColor(str: String): Boolean {
    return EYE_COLORS.contains(str)
}

private fun checkYear(str: String, start: Int, end: Int): Boolean {
    return str.toInt() in start..end
}

private fun checkHeight(str: String): Boolean {
    return when {
        str.contains("cm") -> {
            str.split("cm")[0].toInt() in 150..193
        }
        str.contains("in") -> {
            str.split("in")[0].toInt() in 59..76
        }
        else -> false
    }
}
