package com.shravan.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shravan.calculator.CalculatorState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mariuszgromada.math.mxparser.Expression

class CalculatorViewModel: ViewModel() {

    private val _state :MutableStateFlow<CalculatorState> = MutableStateFlow(
        CalculatorState.Initial
    )
    val state = _state.asStateFlow()

    private var expression = ""
    fun processCommand(command: CalculatorCommand){
        Log.d("CalculatorViewModel","Command: $command")
        when (command){
            CalculatorCommand.Clear -> {
                expression = ""
                _state.value = CalculatorState.Initial
            }
            CalculatorCommand.Evaluate -> {
                val result = evaluate()
                _state.value = if (result != null){
                    Success(result = result)
                }else{
                    Error(expression = expression)
                }
            }
            is CalculatorCommand.Input -> {
                val symbol = if (command.symbols != Symbol.PARENTHESIS){
                    command.symbols.value
                }else{
                    getCorrectParenthises()
                }
                expression += symbol
                _state.value = Input(
                    expression = expression,
                    result = evaluate() ?: ""
                )
            }
            CalculatorCommand.Delete -> {
                if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)

                    if (expression.isEmpty()) {
                        _state.value = CalculatorState.Initial
                    } else {
                        _state.value = CalculatorState.Input(
                            expression = expression,
                            result = evaluate() ?: ""
                        )
                    }
                }
            }
        }
    }

    private fun evaluate(): String? {
        if (expression.isBlank()) return null

        val result = Expression(expression).calculate()

        return if (result.isNaN() || result.isInfinite()) {
            null
        } else {
            val resultString = result.toString()
            if (resultString.endsWith(".0")) {
                resultString.dropLast(2)
            } else {
                resultString
            }
        }
    }

    fun getCorrectParenthises(): String{

        val openCount = expression.count{it == '('}
        val closeCount = expression.count {it == ')'}
        return when{

            expression.isEmpty() -> "("
            expression.last().let { !it.isDigit() && it != ')' } -> "("
            openCount > closeCount -> ")"
            else -> "("
        }
    }
}

sealed interface CalculatorState {
    data object Initial: CalculatorState

    data class Input(
        val expression: String,
        val result: String
    ): CalculatorState

    data class Success(val result: String): CalculatorState

    data class Error(val expression: String): CalculatorState
}
sealed interface CalculatorCommand {
    data object Clear: CalculatorCommand
    data object Delete: CalculatorCommand
    data object Evaluate: CalculatorCommand
    data class Input(val symbols: Symbol): CalculatorCommand
}

enum class Symbol(val value: String) {
    DIGIT_0( "0"),
    DIGIT_1( "1"),
    DIGIT_2( "2"),
    DIGIT_3( "3"),
    DIGIT_4( "4"),
    DIGIT_5( "5"),
    DIGIT_6( "6"),
    DIGIT_7( "7"),
    DIGIT_8( "8"),
    DIGIT_9( "9"),
    ADD( "+"),
    SUB( "-"),
    MULTIPLY( "*"),
    DIVIDE( "/"),
    PERCENT( "%"),
    PARENTHESIS(value = "()"),
    DECIMAL(value = "."),
    EULER(value = "e"),
    SINE(value = "sin("),
    PI(value = "pi")
}
data class Display(
    val expression: String,
    val result: String
)