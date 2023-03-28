package com.snehal.android.photosapp.ui.desc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.snehal.android.photosapp.ViewModel
import com.snehal.android.photosapp.data.model.Data
import com.snehal.android.photosapp.data.model.Product
import com.snehal.android.photosapp.databinding.FragmentDescBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescFragment : Fragment() {

    private lateinit var binding: FragmentDescBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { viewModel.getDescData(it.getInt("dataId")) }
        viewModel.productData.observe(viewLifecycleOwner, { product->
            updateUi(product = product)
        })
    }

    private fun updateUi(product: Product) {
        binding.progressBarDesc.visibility = View.GONE
        binding.textViewDescBody.text = product.description
        binding.textViewDescTitle.text = product.title
        Picasso.get()
            .load(product.images.get(0))
            .into(binding.imgDescPhoto)
    }

    companion object {
        fun newInstance(): DescFragment {
            return DescFragment()
        }
    }
}