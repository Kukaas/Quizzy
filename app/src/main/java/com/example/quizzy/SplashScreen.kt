package com.example.quizzy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Use Handler with the Main Looper to run code on the main thread
        Handler(Looper.getMainLooper()).postDelayed({
            // Start MainActivity after a 3-second delay
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Close SplashScreen activity
        }, 3000)  // 3000 milliseconds = 3 seconds
    }
}
