package com.snehal.android.photosapp.ui.desc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.snehal.android.photosapp.databinding.FragmentDescBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescFragment : Fragment() {

    private lateinit var binding: FragmentDescBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi(arguments)
    }

    private fun updateUi(arguments: Bundle?) {
        binding.textViewDescAlbum.text = "AlbumId: " + arguments?.getInt("dataAlbumId").toString()
        binding.textViewDescTitle.text = arguments?.getString("dataTitle")
        Picasso.get()
            .load(arguments?.getString("dataUrl"))
            .into(binding.imgDescPhoto)
    }

    companion object {
        fun newInstance(): DescFragment {
            return DescFragment()
        }
    }
}