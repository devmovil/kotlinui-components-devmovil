package com.rnnzzo.uxdesign.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
        gotoMainActivity()
    }

    fun gotoMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}