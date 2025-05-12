package com.example.zenoradiostreamer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var playButton: Button
    private lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.play_button)
        stopButton = findViewById(R.id.stop_button)

        playButton.setOnClickListener {
            startService(Intent(this, RadioPlayerService::class.java))
            Toast.makeText(this, "Streaming started", Toast.LENGTH_SHORT).show()
        }

        stopButton.setOnClickListener {
            stopService(Intent(this, RadioPlayerService::class.java))
            Toast.makeText(this, "Streaming stopped", Toast.LENGTH_SHORT).show()
        }
    }
}