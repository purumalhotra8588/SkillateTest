package com.example.skillatetest.acitivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.skillatetest.R
import com.example.skillatetest.SignUpFragment
import com.example.skillatetest.databinding.ActivityMainBinding
import com.example.skillatetest.fragments.BookDescriptionFragment
import com.example.skillatetest.fragments.BookFragment
import com.example.skillatetest.fragments.LoginFragment
import com.example.skillatetest.viewmodel.TestViewModel
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
                viewModel.logout.value = false
            }
        }

        viewModel.openLogin.observe(this) { shouldOpenLogin ->
            if (shouldOpenLogin) {
                replaceFragment(signupFragment)
                viewModel.openLogin.value = false
            }
        }

        viewModel.openBookPage.observe(this) { shouldOpenBookPage ->
            if (shouldOpenBookPage) {
                replaceFragment(bookFragment)
                viewModel.openBookPage.value = false
            }
        }

        viewModel.openDescription.observe(this) { shouldOpenDescription ->
            if (shouldOpenDescription) {
                replaceFragment(bookDescriptionFragment)
                viewModel.openDescription.value = false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTag = fragment.javaClass.simpleName
        Log.d("FragmentReplace", "Replac frag:$fragmentTag")
            supportFragmentManager.beginTransaction()
                .replace(R.id.flContainer, fragment, fragmentTag)
                .addToBackStack(null)
                .commit()

    }


}
