package com.example.helloworldapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var input1: EditText? = null
    private var input2: EditText? = null
    private var output: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        output = findViewById(R.id.result)

        val btnAdd = findViewById<Button>(R.id.addition)
        val btnSub = findViewById<Button>(R.id.subtraction)
        val btnMul = findViewById<Button>(R.id.multiplication)
        val btnDiv = findViewById<Button>(R.id.division)

        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMul.setOnClickListener(this)
        btnDiv.setOnClickListener(this)
    }

    override fun onClick(btn: View?) {
        if (input1?.text.toString() == "" || input2?.text.toString() == "") output?.text = "Please provide valid number"
        else {
            val a = input1?.text.toString().toDouble()
            val b = input2?.text.toString().toDouble()
            val result: String = when(btn?.id) {
                R.id.addition -> add(a, b).toString()
                R.id.subtraction -> subtract(a, b).toString()
                R.id.multiplication -> multiply(a,b ).toString()
                R.id.division -> {
                    if (b.toString() == "0.0") "Cannot perform operation division by zero."
                    else divide(a, b).toString()
                }
                else -> add(a, b).toString()
            }
            output?.text = result
        }
    }

    fun add(a: Double, b: Double): Double {
        return a.plus(b)
    }

    fun subtract(a: Double, b: Double): Double {
        return a.minus(b)
    }

    fun multiply(a: Double, b: Double): Double {
        return a.times(b)
    }

    fun divide(a: Double, b: Double): Double {
        return a.div(b)
    }
}