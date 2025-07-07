package com.koraspond.accessibilitycallmonitor.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class MyAccessibilityService: AccessibilityService() {
    companion object {
        var isServiceRunning: Boolean = false
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        isServiceRunning = true
    }

    override fun onUnbind(intent: Intent?): Boolean {
        isServiceRunning = false
        return super.onUnbind(intent)
    }

    private var isSimulatedRecording = false

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if(event!=null){
            val text = event.text.joinToString(" ")
            if(!isSimulatedRecording && text.contains("call",true)){
                isSimulatedRecording=true
                simulateRecordingStart()

            }
            else if(isSimulatedRecording && text.contains("end", ignoreCase = true)){
                isSimulatedRecording=false
                simulateRecordingStop()
            }

        }
    }
    private fun simulateRecordingStart() {
        Log.d("SIMULATION", "Started simulated call recording")

    }

    private fun simulateRecordingStop() {
        Log.d("SIMULATION", "Stopped simulated call recording")

    }

    override fun onInterrupt() {
        Log.d("ACCESS_SERVICE", "Service Interrupted")
    }
}