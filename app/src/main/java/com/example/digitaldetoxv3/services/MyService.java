package com.example.digitaldetoxv3.services;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class MyService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        try {
            Log.d("TEST", "à l'aide");
            if (event.getPackageName().equals("com.google.android.youtube")) {
                Log.d("TEST", "premier test");
                // Restricts from clicking the Shorts tab, but not playing any shorts in Home or Subscriptions page
                if (event.getSource().isSelected()) {
                    performGlobalAction(GLOBAL_ACTION_BACK);
                }
            }
        } catch (Throwable err) {
            Log.d("TEST", err.toString());
        }
    }

    @Override
    public void onInterrupt() {
        Log.d("TEST", "Service Interrupted :(");
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }

    @Override
    public void onServiceConnected() {
        Log.d("TEST", "Service Connected !");
    }
}