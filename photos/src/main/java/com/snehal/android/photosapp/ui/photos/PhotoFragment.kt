package com.snehal.android.photosapp.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.snehal.android.photosapp.R
import com.snehal.android.photosapp.ViewModel
import com.snehal.android.photosapp.data.model.PhotosData
import com.snehal.android.photosapp.databinding.FragmentPhotoBinding
import com.snehal.android.photosapp.ui.desc.DescFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private lateinit var binding: FragmentPhotoBinding

    private val viewModel: ViewModel by viewModels()
    private lateinit var assetsAdapter: AssetsAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoBinding.inflate(layoutInflater)
        viewModel.getPhotosData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apiData.observe(viewLifecycleOwner, { data->
            bindListData(photosData = data)
        })
    }

    private fun bindListData(photosData: List<PhotosData>) {
        binding.progressBarAssets.visibility = View.GONE
        if (!(photosData.isNullOrEmpty())) {
            assetsAdapter = AssetsAdapter(
                photosData,
                listener = AssetsAdapter.OnClickListener { id,albumId,url,title ->
                    val bundle = Bundle()
                    bundle.putInt("dataId", id)
                    bundle.putInt("dataAlbumId", albumId)
                    bundle.putString("dataUrl", url)
                    bundle.putString("dataTitle", title)
                    val fragment = DescFragment.newInstance()
                    fragment.arguments = bundle
                    requireFragmentManager().beginTransaction().apply {
                        replace(R.id.fragmentContainerHome, fragment, "Market_Fragment")
                        addToBackStack("Market_Fragment")
                        commit()
                    }
                })
            binding.recyclerViewAssets.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
                this.adapter=assetsAdapter
            }
        } else {
            binding.recyclerViewAssets.visibility = View.GONE
            binding.noDataImageAssets.visibility = View.VISIBLE
        }
    }

}
