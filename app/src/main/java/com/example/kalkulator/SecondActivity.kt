package com.example.kalkulator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

@Suppress("DEPRECATION")
class SecondActivity : AppCompatActivity() {

    private lateinit var display: EditText

    private var operator = ""
    private var value1 = 0.0
    private var value2 = 0.0
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        supportActionBar?.hide()
        display = findViewById(R.id.editTextDisplay)

        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonNegative: Button = findViewById(R.id.buttonNegative)
        val buttonPercent: Button = findViewById(R.id.buttonPercent)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)
        val buttonDelete: Button = findViewById(R.id.buttonDelete)
        val buttonGoBack: Button = findViewById(R.id.buttonGoBack)

        val numberButtons = listOf(
            findViewById<Button>(R.id.button0), findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2), findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4), findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6), findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8), findViewById<Button>(R.id.button9)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                display.append((it as Button).text)
            }
        }

        buttonDecimal.setOnClickListener {
            display.append(".")
        }

        buttonClear.setOnClickListener {
            display.text.clear()
            operator = ""
            value1 = 0.0
            value2 = 0.0
            result = 0.0
        }

        buttonNegative.setOnClickListener {
            val text = display.text.toString()
            if (text.isNotEmpty()) {
                try {
                    val number = text.toDouble() * -1
                    // Cek apakah hasilnya bilangan bulat atau desimal
                    if (number % 1 == 0.0) {
                        // Jika bilangan bulat, tampilkan tanpa .0
                        display.setText(number.toInt().toString())
                    } else {
                        // Jika bilangan desimal, tampilkan dengan koma
                        display.setText(number.toString())
                    }
                } catch (e: Exception) {
                    display.setText("Error")
                }
            }
        }

        buttonDelete.setOnClickListener {
            val text = display.text.toString()
            if (text.isNotEmpty()) {
                display.setText(display.text.substring(0, display.text.length - 1))
            }
        }

        buttonPercent.setOnClickListener {
            val text = display.text.toString()
            if (text.isNotEmpty()) {
                display.setText((text.toDouble() / 100).toString())
            }
        }

        val operatorButtons = mapOf(
            buttonAdd to "+",
            buttonSubtract to "-",
            buttonMultiply to "*",
            buttonDivide to "/"
        )

        operatorButtons.forEach { (button, op) ->
            button.setOnClickListener {
                val text = display.text.toString()
                if (text.isNotEmpty()) {
                    value1 = text.toDouble()
                    operator = op
                    display.append(" $op ")  // Tambahkan operator ke tampilan
                }
            }
        }

        buttonEquals.setOnClickListener {
            try {
                val text = display.text.toString()
                if (text.contains(operator)) {
                    // Pisahkan angka pertama, operator, dan angka kedua dari tampilan
                    val parts = text.split(" ")
                    if (parts.size == 3) {
                        value1 = parts[0].toDouble()
                        operator = parts[1]
                        value2 = parts[2].toDouble()

                        result = when (operator) {
                            "+" -> value1 + value2
                            "-" -> value1 - value2
                            "*" -> value1 * value2
                            "/" -> value1 / value2
                            else -> 0.0
                        }

                        // Tampilkan hasil, cek apakah hasilnya bilangan bulat atau desimal
                        if (result % 1 == 0.0) {
                            // Jika hasil bilangan bulat, tampilkan tanpa .0
                            display.setText(result.toInt().toString())
                        } else {
                            // Jika hasil bilangan desimal, tampilkan dengan koma
                            display.setText(result.toString())
                        }
                    }
                }
            } catch (e: Exception) {
                display.setText("Error")
            }
        }


        buttonGoBack.setOnClickListener {
            finish() // Close the current activity and return to the previous one
        }
    }
}
