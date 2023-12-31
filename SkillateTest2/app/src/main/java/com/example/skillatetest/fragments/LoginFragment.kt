package com.example.skillatetest.fragments

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.skillatetest.databinding.ActivityLoginBinding
import com.example.skillatetest.R
import com.example.skillatetest.viewmodel.TestViewModel


class LoginFragment : BaseFragment<TestViewModel, ActivityLoginBinding>() {
    companion object {
        const val TAG = "LoginFragment"
    }


    var showPassword = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true

        listeners()
    }

    private fun listeners() {

        binding.ivShowPassword.setOnClickListener {

            if (!showPassword) {
                binding.etLoginPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_TEXT
                showPassword = true
                binding.ivShowPassword.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primary
                    )
                )
                binding.etLoginPassword.setText(binding.etLoginPassword.text.toString())
            } else {
                binding.etLoginPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                showPassword = false

                binding.ivShowPassword.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
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

        binding.btLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString().trim()
            val password = binding.etLoginPassword.text.toString().trim()

            val isEmailValid = isValidEmail(email)

            if (!isEmailValid) {
                binding.etLoginEmail.error = "Please enter a valid email address"
            }

            if (password.isNullOrEmpty()) {
                binding.etLoginPassword.error = "Please Enter password"
            }

            if (isEmailValid && password.isNotEmpty()) {
                // Both email and password are valid, proceed with login
                login(email, password)

                binding.btLogin.isClickable = false
                binding.tvSignup.isClickable = false
            }
        }

        binding.tvSignup.setOnClickListener {
            viewModel.openLogin.value = true
        }


    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    private fun login(email: String, password: String) {

        viewModel.openBookPage.value = true
    }


    override fun getViewModel() = TestViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityLoginBinding.inflate(inflater, container, false)


}
