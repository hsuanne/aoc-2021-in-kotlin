import kotlin.math.pow

fun main() {

    val data = readInput("day03_input")

    println(part1(data))

    println(part2(data))
}

private fun part1(input: List<String>): Int {
    val gammaRateBinary = getGammaRateBinary(input)
    val gammaRateDecimal = getDecimal(gammaRateBinary)
    val epsilonRateBinary = getEpsilonRateBinary(gammaRateBinary)
    val epsilonRateDecimal = getDecimal(epsilonRateBinary)
    return gammaRateDecimal * epsilonRateDecimal
}

private fun part2(input: List<String>): Int {
    val gamma = filterByNum(input, false)
    val epsilon = filterByNum(input, true)
    val gammaDecimal = getDecimal(gamma.split("").dropLast(1).drop(1).map { it.toInt() })
    val epsilonDecimal = getDecimal(epsilon.split("").dropLast(1).drop(1).map { it.toInt() })
    return gammaDecimal * epsilonDecimal
}

private fun filterByNum(data: List<String>, isLeast:Boolean): String{
    var newData = data.toMutableList()
    val bitLength = data[0].length
    for (i in 0 until bitLength) {
        if (newData.size == 1) break
            var num = getMostCommonNumInBit(newData, i)
        if (isLeast) {
            num = if (num == 0) 1 else 0
        }
            newData = newData.filter {
                it[i].toString() == num.toString()
            }.toMutableList()
    }
    return newData[0]
}

private fun getDecimal(data: List<Int>): Int {
    var p = 0
    var res = 0
    for (i in data.indices.reversed()) {
        if (data[i] == 1) res += 2.0.pow(p).toInt()
        p += 1
    }
    return res
}

private fun getEpsilonRateBinary(data: List<Int>): List<Int> {
    val newData = data.toMutableList()
    for (d in data.indices) {
        newData[d] = if (data[d] == 0) 1 else 0
    }
    return newData
}

private fun getGammaRateBinary(data: List<String>): List<Int> {
    val numbers = mutableListOf<String>()
    val result = mutableListOf<Int>()
    for (i in data[0].indices) {
        numbers.clear()
        for (str in data) {
            numbers.add(str[i].toString())
        }
        val res = getMostCommonNum(numbers)
        result.add(res)
    }
    return result
}

private fun getMostCommonNumInBit(data: List<String>, index:Int): Int {
    val numbers = mutableListOf<String>()
        for (str in data) {
            numbers.add(str[index].toString())
        }
        return getMostCommonNum(numbers)
}

private fun getMostCommonNum(data: List<String>): Int {
    var zero = 0
    var one = 0
    for (d in data) {
        if (d == "0") zero += 1
        if (d == "1") one += 1
    }
    return if (zero > one) 0
    else 1
}