package com.example.digitaldetoxv3.services

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class MyService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.packageName == "com.google.android.youtube") {
            // Blocks shorts only when you click on Shorts tab
            if (event.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED && event.text.toString().contains("Shorts")) {
                Log.d("TEST", "SHORTS BLOCKED")
                performGlobalAction(GLOBAL_ACTION_BACK)
                return
            }
            // Blocks shorts when #shorts is in the title
            if (event.source?.contentDescription?.contains("#shorts") == true) {
                Log.d("TEST", "SHORTS BLOCKED")
                performGlobalAction(GLOBAL_ACTION_BACK)
                return
            }

            if (event.source?.text != null && event.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
                Log.d("TEST", "SHORTS BLOCKED")
                performGlobalAction(GLOBAL_ACTION_BACK)
                return
            }
        }
    }

    override fun onInterrupt() {
        Log.d("TEST", "Service Interrupted :(")
        // Reconnect the service if interrupted
        val intent = Intent(applicationContext, MyService::class.java)
        startService(intent)
    }

    override fun onServiceConnected() {
        Log.d("TEST", "Service Connected !")
    }
}
