package com.koraspond.accessibilitycallmonitor
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.koraspond.accessibilitycallmonitor.service.MyAccessibilityService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statusText = findViewById<TextView>(R.id.statusText)
        val enableBtn = findViewById<Button>(R.id.enableBtn)

        enableBtn.setOnClickListener {
            showInstructionDialog()
        }

        updateServiceStatus(statusText)
    }

    private fun showInstructionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Enable Accessibility Service")
            .setMessage(
                "To enable Accessibility Call Monitor:\n\n" +
                        "1. Scroll down to *Installed Apps or Downloaded Service*\n" +
                        "2. Tap on *Accessibility Call Monitor*\n" +
                        "3. Turn the switch ON and confirm\n\n" +
                        "We’ll try to open the exact page for you."
            )
            .setPositiveButton("Proceed") { _, _ ->
                try {
                    // Attempt to deep-link to your service settings (may not work on all devices)
                    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                        data = Uri.parse("package:$packageName")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(intent)
                } catch (e: Exception) {
                    // Fallback to general settings
                    val fallbackIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                    startActivity(fallbackIntent)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateServiceStatus(statusText: TextView) {
        val isEnabled = MyAccessibilityService.isServiceRunning
        statusText.text = if (isEnabled) {
            "Accessibility Call Monitor is ENABLED"
        } else {
            "Accessibility Call Monitor is NOT enabled"
        }
    }
}