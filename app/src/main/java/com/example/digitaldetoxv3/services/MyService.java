package com.example.digitaldetoxv3.services;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;

public class MyService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getPackageName().equals("com.google.android.youtube")) {
            // Restricts from clicking the Shorts tab, but not playing any shorts in Home or Subscriptions page
            if (event.getSource().isSelected()) {
                performGlobalAction(GLOBAL_ACTION_BACK);
            }
        }
    }

    @Override
    public void onInterrupt() {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
    }
}