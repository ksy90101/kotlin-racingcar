package domain.caculator

import java.util.*
import java.util.function.BinaryOperator

enum class Operator(private val symbol: String, private val calculate: BinaryOperator<Int>) {
    PLUS("+", { number1, number2 -> number1 + number2 }),
    MINUS("-", { number1, number2 -> number1 - number2 }),
    MULTIPLE("*", { number1, number2 -> number1 * number2 }),
    DIVISION("/", { number1, number2 ->
        if (number2 == 0) {
            throw IllegalArgumentException("나누기의 분모가 0이 될수 없습니다.")
        }
        number1 / number2
    });

    companion object {
        private val OPERRATORS = values().associateBy { it.symbol }

        @JvmStatic
        fun of(symbol: String): Optional<Operator> {
            return Optional.ofNullable(OPERRATORS[symbol])
        }
    }

    fun calculate(number1: Int, number2: Int): Int {
        return this.calculate.apply(number1, number2)
    }
}