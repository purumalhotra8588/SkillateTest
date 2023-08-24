package com.example.skillatetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.skillatetest.databinding.ActivityMainBinding
import com.spyneai.dashboard.ui.base.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: TestViewModel
    lateinit var loginFragment: LoginFragment
    lateinit var signupFragment: SignUpFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(TestViewModel::class.java)

        loginFragment = LoginFragment()
        signupFragment = SignUpFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, loginFragment)
            .commit()

        viewModel.openLogin.observe(this){
            if(it){
                supportFragmentManager.beginTransaction()
                    .remove(loginFragment)
                    .add(R.id.flContainer, signupFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

    }
}