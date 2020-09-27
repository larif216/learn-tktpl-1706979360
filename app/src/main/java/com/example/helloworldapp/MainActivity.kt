package com.example.helloworldapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var a: Double? = null
    private var b: Double? = null
    private var result: Double? = null
    private var input1: EditText? = null
    private var input2: EditText? = null
    private var output: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        output = findViewById(R.id.result)
    }

    private fun convertToDouble() {
        a = input1?.text.toString().toDouble()
        b = input2?.text.toString().toDouble()
    }

    fun add(view: View) {
        convertToDouble()
        result = b?.let { a?.plus(it) }
        output?.text = result.toString()
    }

    fun subtract(view: View) {
        convertToDouble()
        result = b?.let { a?.minus(it) }
        output?.text = result.toString()
    }

    fun multiply(view: View) {
        convertToDouble()
        result = b?.let { a?.times(it) }
        output?.text = result.toString()
    }

    fun division(view: View) {
        if (input2?.text.toString() == "0") output?.text = "Error: division by zero"
        else {
            convertToDouble()
            result = b?.let { a?.div(it) }
            output?.text = result.toString()
        }
    }
}