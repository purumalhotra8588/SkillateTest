package com.example.skillatetest

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.skillatetest.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var showPassword = false


        binding.ivShowPassword.setOnClickListener {

            if (!showPassword) {
                binding.etLoginPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_TEXT
                showPassword = true
                binding.ivShowPassword.setColorFilter(ContextCompat.getColor(this, R.color.primary))
                binding.etLoginPassword.setText(binding.etLoginPassword.text.toString())
            } else {
                binding.etLoginPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                showPassword = false

                binding.ivShowPassword.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.eye_grey
                    )
                )
                binding.etLoginPassword.setText(binding.etLoginPassword.text.toString())

            }
        }
        if (!showPassword) {
            binding.etLoginPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            binding.etLoginPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_TEXT
        }

        val appName = getString(R.string.app_name)
        binding.tvNewToApp.text = getString(R.string.new_to, appName)


        listeners()
    }

    private fun listeners() {
        binding.btLogin.setOnClickListener {

            when {
                binding.etLoginEmail.text.toString().isEmpty() -> {
                    binding.etLoginEmail.error = "Please enter email id"

                }
                binding.etLoginPassword.text.toString().isEmpty() -> {
                    binding.etLoginPassword.error = "Please enter password"

                }
                else -> {
                    login(
                        binding.etLoginEmail.text.toString().trim(),
                        binding.etLoginPassword.text.toString().trim()
                    )

                    binding.btLogin.isClickable = false
                    binding.tvSignup.isClickable = false
                    binding.tvForgotPassword.isClickable = false
                    binding.btSignInUsingOtp.isClickable = false
                    binding.tvterms.isClickable = false
                }
            }
        }


        binding.tvSignup.setOnClickListener {

        }

        binding.btSignInUsingOtp.setOnClickListener {

        }

        binding.tvForgotPassword.setOnClickListener {

        }



    }

    private fun login(email: String, password: String) {
        val properties = HashMap<String, Any?>()
            .apply {
                this.put("email", email)
            }


    }


}
