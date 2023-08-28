package com.example.skillatetest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillatetest.data.Book
import com.example.skillatetest.adapter.BookAdapter
import com.example.skillatetest.databinding.FragmentBookBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.skillatetest.adapter.OnItemClickListener
import com.example.skillatetest.viewmodel.TestViewModel
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class BookFragment : BaseFragment<TestViewModel, FragmentBookBinding>(), OnItemClickListener {

    lateinit var adapter: BookAdapter

    private var isAscending = true

    private var jsonData : String? =null

    private var originalData = ArrayList<Book>()

    companion object {
        const val TAG = "BookFragment"
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if(jsonData==null){
            jsonData = loadJSONFromAsset("books.json")
            originalData.addAll(parseJSON(jsonData))
        }

        adapter = BookAdapter(requireContext(), originalData, this)

        binding.rvBook.adapter = adapter
        binding.rvBook.layoutManager =
            LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL,
                false
            )

        listners()


    }

    private fun listners() {

        binding.btLogout.setOnClickListener {
            Toast.makeText(requireContext(),"You have logged out successfully",Toast.LENGTH_SHORT).show()
            viewModel.logout.value = true
        }

        binding.llFilter.setOnClickListener {
            if (binding.flSort.visibility != View.VISIBLE) {
                binding.flSort.visibility = View.VISIBLE
            } else {
                binding.flSort.visibility = View.GONE
            }
        }

        binding.llOrder.setOnClickListener {
            isAscending = !isAscending
            updateSorting()
            binding.tvOrder.text = if (isAscending) "A-Z" else "Z-A"
        }

        binding.cbFavs.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                updateSorting()
            }else{
                adapter.updateData(originalData.filter { !it.isFavorite })
            }
        }

        binding.cbHits.setOnCheckedChangeListener { _, isChecked ->
            updateSorting()
        }

        binding.cbTitle.setOnCheckedChangeListener { _, isChecked ->
            updateSorting()
        }
    }

    private fun updateSorting() {

        var sortedList:List<Book> = when {
            binding.cbTitle.isChecked -> {
                if (isAscending) {
                    originalData.sortedBy { it.title }
                } else {
                    originalData.sortedByDescending { it.title }
                }
            }
            binding.cbHits.isChecked -> {
                if (isAscending) {
                    originalData.sortedBy { it.hits }
                } else {
                    originalData.sortedByDescending { it.hits }
                }
            }
            binding.cbFavs.isChecked -> {
                if (isAscending) {
                    originalData.sortedBy { it.isFavorite }
                } else {
                    originalData.sortedByDescending { it.isFavorite }
                }
            }
            else -> originalData // Default sorting (no checkbox selected)
        }

        if(!binding.cbFavs.isChecked){
            sortedList = sortedList.filter { !it.isFavorite }
        }
        adapter.updateData(sortedList)
    }


    override fun onItemClick(view: View, position: Int, data: Any?) {
        val clickedBook = data as Book

        viewModel.openDescription.value = true
        viewModel.selectedBook = clickedBook

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

    private fun parseJSON(json: String?): List<Book> {
        val gson = Gson()
        val bookListType = object : TypeToken<List<Book>>() {}.type
        return gson.fromJson(json, bookListType)
    }


    override fun getViewModel() = TestViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBookBinding.inflate(inflater, container, false)

}