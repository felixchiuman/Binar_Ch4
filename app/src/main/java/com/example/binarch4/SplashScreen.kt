package com.example.binarch4
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.binarch4.activity.LoginActivity
import com.example.binarch4.activity.MainActivity
import com.example.binarch4.onboarding.OnBoarding

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {

    private val sharedPref = "onBoardingScreen"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({

            val onBoardingScreen: SharedPreferences = this.getSharedPreferences(sharedPref, MODE_PRIVATE)
            val isFirstTime: Boolean = onBoardingScreen.getBoolean("firstTime", true)

            if (isFirstTime){
                val editor: SharedPreferences.Editor = onBoardingScreen.edit()
                editor.putBoolean("firstTime", false)
                editor.commit()

                val intent = Intent(this, OnBoarding::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        },3000)
    }
}