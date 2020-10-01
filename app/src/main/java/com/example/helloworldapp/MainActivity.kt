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

    companion object {
        const val TAG = "MainActivity"
        const val INITIAL_TIME = "00:00:00"
    }
    private var textView: TextView? = null
    private var buttonStart: Button? = null
    private var buttonStop: Button? = null
    private var buttonReset: Button? = null
    private val br = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i(TAG, "Intent Received!")
            val value = intent?.getStringExtra("Time")
            updateTextView(value)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        Log.i(TAG, "Created!")
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(br, IntentFilter(StopwatchService.STOPWATCH_BR))
        Log.i(TAG, "Resumed!")
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(br)
        Log.i(TAG, "Paused!")
    }

    private fun init() {
        textView = findViewById(R.id.time_view)
        textView?.text = INITIAL_TIME

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
        Log.i(TAG, "Button reset was clicked!")
        stopService(Intent(this, StopwatchService::class.java))
        textView?.text = INITIAL_TIME
        buttonStart?.isEnabled = true
        buttonStop?.isEnabled = false
        buttonReset?.isEnabled = false
    }

    private fun stopStopwatch() {
        Log.i(TAG, "Button stop was clicked!")
        stopService(Intent(this, StopwatchService::class.java))
        buttonStart?.isEnabled = true
        buttonStop?.isEnabled = false
        buttonReset?.isEnabled = true
    }

    private fun startStopwatch() {
        Log.i(TAG, "Button start was clicked!")
        startService(Intent(this, StopwatchService::class.java))
        buttonStart?.isEnabled = false
        buttonStop?.isEnabled = true
        buttonReset?.isEnabled = true
    }

    private fun updateTextView(value: String?) {
        textView?.text = value
    }
}