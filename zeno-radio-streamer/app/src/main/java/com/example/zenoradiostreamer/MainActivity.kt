package com.example.zenoradiostreamer

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainIframeWebView = findViewById<WebView>(R.id.main_iframe_webview)
        mainIframeWebView.settings.javaScriptEnabled = true
        mainIframeWebView.settings.domStorageEnabled = true
        mainIframeWebView.webViewClient = WebViewClient()
        mainIframeWebView.loadUrl("https://zeno.fm/player/b-trammell-radio")

        val webView = findViewById<WebView>(R.id.webview)
        val btnWebView = findViewById<Button>(R.id.btn_webview)
        val btnNative = findViewById<Button>(R.id.btn_native)

        btnWebView.setOnClickListener {
            webView.visibility = WebView.VISIBLE
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://listen.zeno.fm/player/app-ffjtjj")
        }

        btnNative.setOnClickListener {
            webView.visibility = WebView.GONE
            val intent = Intent(this, RadioPlayerService::class.java)
            startService(intent)
        }
    }
}