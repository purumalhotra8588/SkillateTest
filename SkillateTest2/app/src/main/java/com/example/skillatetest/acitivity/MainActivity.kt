package com.example.skillatetest.acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.skillatetest.R
import com.example.skillatetest.SignUpFragment
import com.example.skillatetest.viewmodel.TestViewModel
import com.example.skillatetest.fragments.BookFragment
import com.example.skillatetest.fragments.LoginFragment
import com.example.skillatetest.databinding.ActivityMainBinding
import com.example.skillatetest.fragments.BookDescriptionFragment
import com.example.skillatetest.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TestViewModel
    lateinit var loginFragment: LoginFragment
    lateinit var signupFragment: SignUpFragment
    lateinit var bookFragment: BookFragment
    lateinit var bookDescriptionFragment: BookDescriptionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(TestViewModel::class.java)

        loginFragment = LoginFragment()
        signupFragment = SignUpFragment()
        bookFragment = BookFragment()
        bookDescriptionFragment = BookDescriptionFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, loginFragment)
            .commit()

        viewModel.logout.observe(this) {
            if (it) {
                if (supportFragmentManager.findFragmentById(R.id.flContainer) !is LoginFragment) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, loginFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        viewModel.openLogin.observe(this) {
            if (it) {
                if (supportFragmentManager.findFragmentById(R.id.flContainer) !is SignUpFragment) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, signupFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        viewModel.openBookPage.observe(this) {
            if (it) {
                if (supportFragmentManager.findFragmentById(R.id.flContainer) !is BookFragment) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, bookFragment)
                        .commit()
                }
            }
        }

        viewModel.openDescription.observe(this) {
            if (it) {
                if (supportFragmentManager.findFragmentById(R.id.flContainer) !is BookDescriptionFragment) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flContainer, bookDescriptionFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

    }
}