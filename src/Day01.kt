fun main() {

    val data = readInput("day01_input")

    val input = data.map { it.toInt() }
    
    println(part1(input))

    println(part2(input))
}

private fun part1(input: List<Int>): Int {
    var count = 0
    for (i in 0 until input.size-1){
        if (input[i] < input[i+1]) count += 1
    }
    return count
}

private fun part2(input: List<Int>): Int {
    var count = 0
    for (i in 0 until input.size-3){
        val sumPrev = input[i] + input [i+1] + input[i+2]
        val sumNext = input[i+1] + input[i+2] + input[i+3]

        if (sumPrev < sumNext) count += 1
    }
    return count
}