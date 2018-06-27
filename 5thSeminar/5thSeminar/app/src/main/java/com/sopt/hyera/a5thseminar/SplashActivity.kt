package com.sopt.hyera.a5thseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            //
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 3000) // 밀리세컨드=초*1000
    }
}
