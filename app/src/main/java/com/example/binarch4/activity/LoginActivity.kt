package com.example.binarch4.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarch4.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    val sharedPrefFile = "loginScreen"

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        binding.btnLogin.setOnClickListener{
            val sharedEmailValue = sharedPreferences.getString("email_key","defaultEmail")
            val sharedPassValue = sharedPreferences.getString("pass_key","defaultPass")

            if (sharedEmailValue.equals("defaultEmail") || sharedPassValue.equals("defaultPass")){
                Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show()
            }
            else{
                val mainActivity = Intent(this, MainActivity::class.java)
                startActivity(mainActivity)
            }
        }
        binding.tvSignUp.setOnClickListener {
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
            finish()
        }
    }

}