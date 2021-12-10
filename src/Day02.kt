fun main() {

    val data = readInput("day02_input")

    val input = getMovementFromData(data)

    println(part1(input))

    println(part2(input))
}

private fun part1(input: List<Movement>): Int {
    var horizontal = 0
    var depth = 0
    for (movement in input) {
        if (movement.direction == "forward") horizontal += movement.progress
        if (movement.direction == "down") depth += movement.progress
        if (movement.direction == "up") depth -= movement.progress
    }
    return horizontal * depth
}

private fun part2(input: List<Movement>): Int {
    var horizontal = 0
    var depth = 0
    var aim = 0

    for (movement in input) {
        if (movement.direction == "forward") {
            horizontal += movement.progress
            depth += aim * movement.progress
        }
        if (movement.direction == "down") aim += movement.progress
        if (movement.direction == "up") aim -= movement.progress
        println("$horizontal, $depth")
    }
    return horizontal * depth


}

private fun getMovementFromData(data: List<String>): List<Movement> {
    val movements = mutableListOf<Movement>()
    for (str in data) {
        val direction = str.substringBefore(" ")
        val progress = str.substringAfter(" ").toInt()
        val movement = Movement(direction, progress)
        movements.add(movement)
    }
    return movements
}

class Movement(val direction: String, val progress: Int)