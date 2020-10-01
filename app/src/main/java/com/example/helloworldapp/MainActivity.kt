package com.example.helloworldapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworldapp.services.StopwatchService

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var textView: TextView? = null
    private var buttonStart: Button? = null
    private var buttonStop: Button? = null
    private var buttonReset: Button? = null
    private val br = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i(StopwatchService.TAG, "Intent Received!")
            val value = intent?.getStringExtra("Time")
            updateTextView(value)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(br, IntentFilter(StopwatchService.STOPWATCH_BR))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
    }


    private fun init() {
        textView = findViewById(R.id.time_view)
        textView?.text = "00:00:00"

        buttonStart = findViewById(R.id.start_button)
        buttonStop = findViewById(R.id.stop_button)
        buttonReset = findViewById(R.id.reset_button)
        setListener()
    }

    private fun setListener() {
        buttonStart?.setOnClickListener(this)
        buttonStop?.setOnClickListener(this)
        buttonReset?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.start_button -> startStopwatch()
            R.id.stop_button -> stopStopwatch()
            R.id.reset_button -> resetStopwatch()
            else -> startStopwatch()
        }
    }

    private fun resetStopwatch() {
        TODO("Not yet implemented")
    }

    private fun stopStopwatch() {
        onPause()
    }

    private fun startStopwatch() {
        startService(Intent(this, StopwatchService::class.java))
    }

    private fun updateTextView(value: String?) {
        textView?.text = value
    }


}