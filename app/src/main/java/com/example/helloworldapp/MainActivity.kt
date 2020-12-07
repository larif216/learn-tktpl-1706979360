package com.example.helloworldapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        System.loadLibrary("native-code")

        button.setOnClickListener {
            val reversedString = nativeFun(input.text.toString())
            result.text = reversedString.toString()
        }

    }

    external fun nativeFun(input: String)
}