package com.example.helloworldapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import java.util.*

class StopwatchService : Service() {

    companion object {
        const val TAG: String = "BroadcastService"
        const val STOPWATCH_BR: String = "com.example.helloworldapp.stopwatch_br"
    }
    private var seconds: Int = 0
    private val bi: Intent = Intent(STOPWATCH_BR)
    private val handler = Handler()
    private lateinit var runnable: Runnable

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Service Started!")
        runnable = object: Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, secs)

                seconds++

                bi.putExtra("Time", time)
                sendBroadcast(bi)
                Log.d("Time", time)

                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
        Log.i(TAG, "Service Finished!")
    }
}
