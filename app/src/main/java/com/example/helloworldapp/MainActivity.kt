package com.example.helloworldapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworldapp.network.ApiClient
import com.example.helloworldapp.network.ApiInterface
import com.example.helloworldapp.network.WifiData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {

    private lateinit var wifiManager: WifiManager
    private lateinit var wifiScanReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiScanReceiver = object : BroadcastReceiver() {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                    setWifiList()
                    sendWifiData()
                    unregisterReceiver(this)
                    btn_scan_wifi.isEnabled = true
                }
            }
        }

        btn_scan_wifi.setOnClickListener {
            if (wifiManager.isWifiEnabled) {
                val intentFilter = IntentFilter()
                intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
                registerReceiver(wifiScanReceiver, intentFilter)

                wifiManager.startScan()

                btn_scan_wifi.isEnabled = false
                Toast.makeText(this, "Scanning...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enable your wifi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setWifiList() {
        val scanResult = wifiManager.scanResults
        list_view.adapter = ArrayAdapter(
                this,
                R.layout.list_view_item,
                scanResult.stream().map {
                    it.SSID
                }.collect(Collectors.toList())
        )
    }

    private fun sendWifiData() {
        val scanResult = wifiManager.scanResults
        val apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        for (result in scanResult) {
            val wifiData = WifiData(
                    result.SSID,
                    result.BSSID
            )
            val call = apiInterface.sendWifiData(wifiData)
            call.enqueue(object: Callback<WifiData> {
                override fun onResponse(call: Call<WifiData>, response: Response<WifiData>) {
                    Log.d("Code Status", response.code().toString())
                }

                override fun onFailure(call: Call<WifiData>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}