package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentNumber: String = ""
    private var operator: String? = null
    private var firstOperand: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        setNumberButtonListeners()
        setOperatorButtonListeners()
        setSpecialButtonListeners()
    }

    private fun setNumberButtonListeners() {
        val numberButtons = listOf<Button>(
            findViewById(R.id.button_zero),
            findViewById(R.id.button_one),
            findViewById(R.id.button_two),
            findViewById(R.id.button_three),
            findViewById(R.id.button_four),
            findViewById(R.id.button_five),
            findViewById(R.id.button_six),
            findViewById(R.id.button_seven),
            findViewById(R.id.button_eight),
            findViewById(R.id.button_nine)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        display.text = currentNumber
    }

    private fun setOperatorButtonListeners() {
        val operatorButtons = mapOf(
            R.id.button_plus to "+",
            R.id.button_minus to "-",
            R.id.button_multiply to "*",
            R.id.button_divide to "/"
        )

        operatorButtons.forEach { (id, op) ->
            findViewById<Button>(id).setOnClickListener {
                if (currentNumber.isNotEmpty()) {
                    firstOperand = currentNumber.toInt()
                    currentNumber = ""
                    operator = op
                }
            }
        }

        findViewById<Button>(R.id.button_equal).setOnClickListener {
            if (currentNumber.isNotEmpty() && operator != null && firstOperand != null) {
                val secondOperand = currentNumber.toInt()
                performCalculation(firstOperand!!, secondOperand, operator!!)
            }
        }
    }

    private fun performCalculation(first: Int, second: Int, op: String) {
        val result = when (op) {
            "+" -> first + second
            "-" -> first - second
            "*" -> first * second
            "/" -> if (second != 0) first / second else 0
            else -> 0
        }
        display.text = result.toString()
        resetCalculator()
    }

    private fun resetCalculator() {
        currentNumber = ""
        operator = null
        firstOperand = null
    }

    private fun setSpecialButtonListeners() {
        findViewById<Button>(R.id.button_clear).setOnClickListener {
            resetCalculator()
            display.text = "0"
        }

        findViewById<Button>(R.id.button_ce).setOnClickListener {
            currentNumber = ""
            display.text = "0"
        }

        findViewById<Button>(R.id.button_backspace).setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                currentNumber = currentNumber.dropLast(1)
                display.text = currentNumber.ifEmpty { "0" }
            }
        }
    }
}
