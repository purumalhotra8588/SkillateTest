package com.example.skillatetest

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.skillatetest.databinding.SignupFragmentBinding
import com.google.gson.Gson
import com.example.skillatetest.fragments.BaseFragment
import com.example.skillatetest.viewmodel.TestViewModel
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


class SignUpFragment : BaseFragment<TestViewModel, SignupFragmentBinding>() {

    var regionsList = ArrayList<String>()
    var regionToCountriesMap = HashMap<String, List<Country>>()
    lateinit var spinnerAdapter: ArrayAdapter<String>
    lateinit var countriesAdapter : ArrayAdapter<String>
    var showPassword = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonString = loadJSONFromAsset("countries.json")
        val countryData = parseJSON(jsonString)


        // Group countries by region
        val groupedCountries = countryData.data.values.groupBy { it.region }

        regionsList.add(getString(R.string.select_region))
        // Populate regionsList and regionToCountriesMap
        for ((region, countries) in groupedCountries) {
            regionsList.add(region)
            regionToCountriesMap[region] = countries
        }

        val defaultCountryList = listOf(getString(R.string.select_country))
        val countriesList = defaultCountryList.toMutableList()


        spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            regionsList
        )


        binding.countriesSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            countriesList // Initially, the countries spinner is empty until a region is selected
        )


        binding.regionsSpinner.adapter = spinnerAdapter

        binding.regionsSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedRegion = regionsList[position]
                    val countriesInRegion = regionToCountriesMap[selectedRegion] ?: emptyList()


                    val countriesInRegionNames = countriesInRegion.map { it.country }
                    countriesList.clear()
                    countriesList.addAll(defaultCountryList)
                    countriesList.addAll(countriesInRegionNames)
                    // Update the countries spinner with the countries in the selected region
                     countriesAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        countriesList
                    )
                    countriesAdapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case where nothing is selected
                }
            }
        listeners()


    }


    private fun loadJSONFromAsset(fileName: String): String? {
        val json: String?
        try {
            val inputStream: InputStream = requireContext().assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun parseJSON(json: String?): CountryData {
        val gson = Gson()
        return gson.fromJson(json, CountryData::class.java)
    }


    private fun listeners() {

        binding.ivSignUpShowPassword.setOnClickListener {

            if (!showPassword) {
                binding.etSignupPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_TEXT
                showPassword = true
                binding.ivSignUpShowPassword.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.primary
                    )
                )
                binding.etSignupPassword.setText(binding.etSignupPassword.text.toString())
            } else {
                binding.etSignupPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                showPassword = false

                binding.ivSignUpShowPassword.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.eye_grey
                    )
                )
                binding.etSignupPassword.setText(binding.etSignupPassword.text.toString())

            }
        }
        if (!showPassword) {
            binding.etSignupPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            binding.etSignupPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_TEXT
        }


        binding.tvSignUp.setOnClickListener {
            val email = binding.etName.text.toString().trim()
            val password = binding.etSignupPassword.text.toString().trim()

            val isEmailValid = isValidEmail(email)
            val isPasswordValid = isValidPassword(password)


            if (!isEmailValid) {
                binding.etName.error = "Please enter a valid email address"
            }

            if (!isPasswordValid) {
                binding.etSignupPassword.error = "Password must have at least 8 characters, " +
                        "one number, one special character, " +
                        "one lowercase letter, and one uppercase letter."
            }

            if (isEmailValid && isPasswordValid) {
                // Both email and password are valid, proceed with login
                signup(email, password)

                binding.etName.isClickable = false
                binding.etSignupPassword.isClickable = false
            }
        }

        binding.tvLogin.setOnClickListener {
            requireActivity().onBackPressed()
            spinnerAdapter.clear()
            countriesAdapter.clear()
        }

    }

    private fun signup(email: String, password: String) {
        Toast.makeText(requireContext(), "Signup Successful", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Regex("^(?=.*[0-9])(?=.*[!@#\$%&()])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
        return passwordPattern.matches(password)
    }


    override fun getViewModel() = TestViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = SignupFragmentBinding.inflate(inflater, container, false)

}