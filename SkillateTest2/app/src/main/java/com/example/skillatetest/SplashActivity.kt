package com.spyneai.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.skillatetest.LoginFragment
import com.example.skillatetest.R


class SplashActivity : AppCompatActivity() {

    val TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        setSplash()

    }

    private fun setSplash() {
        Handler().postDelayed({
            val intent = Intent(this, LoginFragment::class.java)
            startActivity(intent)
            finishAffinity()

        }, 500)
    }
}
