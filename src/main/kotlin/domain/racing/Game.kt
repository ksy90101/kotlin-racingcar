package domain.racing

import util.racing.generator.NumberGenerator

class Game(names: List<Name>, private val trial: Int) {
    private val cars: Cars
    private val results: MutableList<Map<Car, String>>

    init {
        validateNames(names)
        validateTrial(trial)
        this.cars = Cars.of(names)
        this.results = mutableListOf()
    }

    fun run(numberGenerator: NumberGenerator): Winners {
        repeat(trial) {
            cars.move(numberGenerator)
            results.add(cars.toResult())
        }

        return cars.toWinners()
    }

    fun getWinnerNames(): List<String> {
        return cars.toWinners().winners.map { it.getName() }
    }

    fun getResult(): List<Map<Car, String>> {
        return results
    }

    private fun validateNames(names: List<Name>) {
        require(names.isNotEmpty()) { throw IllegalArgumentException("차 갯수는 1개 이상이여야 합니다.") }
    }

    private fun validateTrial(trial: Int) {
        require(trial > 0) { throw IllegalArgumentException("시도 횟수는 1번 이상이여야 합니다.") }
    }
}
