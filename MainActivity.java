package com.bubblydialer.crm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIncomingIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIncomingIntent(intent);
    }

    /**
     * Catches intercepted phone calls from Google Maps or shared places
     * and forwards them to the BubblyDialer web interface.
     */
    private void handleIncomingIntent(Intent intent) {
        if (intent == null) return;

        String action = intent.getAction();
        Uri data = intent.getData();

        // 1. Intercept "Call" clicks (tel: scheme)
        if (Intent.ACTION_DIAL.equals(action) || Intent.ACTION_VIEW.equals(action)) {
            if (data != null && "tel".equals(data.getScheme())) {
                String rawPhone = data.getSchemeSpecificPart();
                try {
                    String encodedPhone = URLEncoder.encode(rawPhone, "UTF-8");
                    // Pass the intercepted phone number to the web view
                    this.bridge.getWebView().post(() -> {
                        this.bridge.getWebView().evaluateJavascript(
                            "window.location.search = '?tel=" + encodedPhone + "';",
                            null
                        );
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        // 2. Intercept "Share from Google Maps"
        if (Intent.ACTION_SEND.equals(action) && "text/plain".equals(intent.getType())) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null) {
                try {
                    String encodedText = URLEncoder.encode(sharedText, "UTF-8");
                    this.bridge.getWebView().post(() -> {
                        this.bridge.getWebView().evaluateJavascript(
                            "window.location.search = '?share=" + encodedText + "';",
                            null
                        );
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
