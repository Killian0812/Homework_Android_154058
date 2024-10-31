package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SimpleListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list)

        // Get references to the views
        val editTextNumber: EditText = findViewById(R.id.editTextNumber)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val buttonShow: Button = findViewById(R.id.buttonShow)
        val listViewResults: ListView = findViewById(R.id.listViewResults)
        val textViewError: TextView = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            val inputText = editTextNumber.text.toString()
            if (inputText.isEmpty() || inputText.toIntOrNull() == null || inputText.toInt() < 0) {
                textViewError.text = "Please enter a valid positive integer"
                textViewError.visibility = TextView.VISIBLE
                listViewResults.adapter = null
            } else {
                textViewError.visibility = TextView.GONE
                val n = inputText.toInt()

                val selectedId = radioGroup.checkedRadioButtonId
                val results = when (selectedId) {
                    R.id.radioEven -> getEvenNumbers(n)
                    R.id.radioOdd -> getOddNumbers(n)
                    R.id.radioPerfectSquare -> getPerfectSquares(n)
                    else -> emptyList()
                }

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
                listViewResults.adapter = adapter
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    private fun getPerfectSquares(n: Int): List<Int> {
        val perfectSquares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            perfectSquares.add(i * i)
            i++
        }
        return perfectSquares
    }
}
