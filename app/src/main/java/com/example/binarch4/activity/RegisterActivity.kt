package com.example.binarch4.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarch4.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    val sharedPrefFile = "loginScreen"

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            val email: String = binding.etRegisterEmail.editText?.text.toString()
            val pass: String = binding.etRegisterPassword.editText?.text.toString()

            if (email == "" || pass == ""){
                Toast.makeText(this, "Sign Up Error", Toast.LENGTH_SHORT).show()
            }
            else if (sharedPreferences.contains("email_key")){
                Toast.makeText(this, "This account already registered", Toast.LENGTH_SHORT).show()
            }
            else{
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("email_key", email)
                editor.putString("pass_key", pass)
                editor.apply()
                val successActivity = Intent(this, RegisterSuccess::class.java)
                startActivity(successActivity)
            }
        }
        binding.tvRegister.setOnClickListener {
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
            finish()
        }
    }
}