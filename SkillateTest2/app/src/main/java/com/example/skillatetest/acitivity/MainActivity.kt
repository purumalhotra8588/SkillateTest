package com.example.skillatetest.acitivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
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
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TestViewModel

    // Define your fragments as member variables
    private val loginFragment = LoginFragment()
    private val signupFragment = SignUpFragment()
    private val bookFragment = BookFragment()
    private val bookDescriptionFragment = BookDescriptionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelFactory()).get(TestViewModel::class.java)

        if (savedInstanceState == null) {
            replaceFragment(loginFragment)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.logout.observe(this) { shouldLogout ->
            if (shouldLogout) {
                replaceFragment(loginFragment)
            }
        }

        viewModel.openLogin.observe(this) { shouldOpenLogin ->
            if (shouldOpenLogin) {
                replaceFragment(signupFragment)
            }
        }

        viewModel.openBookPage.observe(this) { shouldOpenBookPage ->
            if (shouldOpenBookPage) {
                replaceFragment(bookFragment)
            }
        }

        viewModel.openDescription.observe(this) { shouldOpenDescription ->
            if (shouldOpenDescription) {
                replaceFragment(bookDescriptionFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTag = fragment.javaClass.simpleName
        Log.d("FragmentReplace", "Replac frag:$fragmentTag")
        val existingFragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        if (existingFragment == null) {
            Log.d("FragmentReplace", "new frag:$fragmentTag")
            supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, fragment, fragmentTag)
                .addToBackStack(null)
                .commit()
        }
    }

}
