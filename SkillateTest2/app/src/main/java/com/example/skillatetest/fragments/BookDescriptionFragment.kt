package com.example.skillatetest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.skillatetest.data.Book
import com.example.skillatetest.databinding.BookDescriptionBindingBinding
import com.example.skillatetest.viewmodel.TestViewModel

class BookDescriptionFragment : BaseFragment<TestViewModel, BookDescriptionBindingBinding>() {


    companion object {
        const val TAG = "BookDescriptionFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true

        binding.tvTitle.text = viewModel.selectedBook?.title

        binding.tvHits.text = viewModel.selectedBook?.hits.toString()

        binding.tvAlias.text = viewModel.selectedBook?.alias.toString()

        binding.tvDescription.text = viewModel.selectedBook?.lastChapterDate.toString()

        Glide.with(requireContext())
            .load(viewModel.selectedBook?.image)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .skipMemoryCache(false)
            .into(binding.skuThumbnail)
    }


    override fun getViewModel() = TestViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = BookDescriptionBindingBinding.inflate(inflater, container, false)

}