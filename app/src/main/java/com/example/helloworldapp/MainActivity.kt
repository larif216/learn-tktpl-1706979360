package com.example.helloworldapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworldapp.services.StopwatchService

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG = "MainActivity"
        const val INITIAL_TIME = "00:00:00"
    }
    private var doubleBackToExitPressedOnce = false
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

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "Stopped!")
    }

    private fun init() {
        textView = findViewById(R.id.time_view)
        textView?.text = INITIAL_TIME

        buttonStart = findViewById(R.id.start_button)
        buttonStop = findViewById(R.id.stop_button)
        buttonReset = findViewById(R.id.reset_button)
        setListener()

        disableButton(intArrayOf(R.id.stop_button, R.id.reset_button))
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
        disableButton(intArrayOf(R.id.stop_button, R.id.reset_button))
    }

    private fun stopStopwatch() {
        Log.i(TAG, "Button stop was clicked!")
        stopService(Intent(this, StopwatchService::class.java))
        disableButton(intArrayOf(R.id.stop_button))
    }

    private fun startStopwatch() {
        Log.i(TAG, "Button start was clicked!")
        startService(Intent(this, StopwatchService::class.java))
        disableButton(intArrayOf(R.id.start_button))
    }

    private fun disableButton(disabledButtons: IntArray) {
        buttonStart?.isEnabled = true
        buttonStop?.isEnabled = true
        buttonReset?.isEnabled = true

        buttonStart?.alpha = 1F
        buttonStop?.alpha = 1F
        buttonReset?.alpha = 1F

        for (id in disabledButtons) {
            findViewById<Button>(id).alpha = .5F
            findViewById<Button>(id).isEnabled = false
        }
    }

    private fun updateTextView(value: String?) {
        textView?.text = value
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
        } else {
            doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable {
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopStopwatch()
        Log.i(TAG, "Destroyed!")
    }
}