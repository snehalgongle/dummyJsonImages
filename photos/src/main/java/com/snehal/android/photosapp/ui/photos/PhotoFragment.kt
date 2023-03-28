package com.snehal.android.photosapp.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.snehal.android.photosapp.R
import com.snehal.android.photosapp.ViewModel
import com.snehal.android.photosapp.data.model.Data
import com.snehal.android.photosapp.data.model.Product
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
            bindListData(data = data)
        })
        binding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.progressBarAssets.visibility = View.VISIBLE
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun bindListData(data: Data) {
        binding.progressBarAssets.visibility = View.GONE
        if (data.products.isNotEmpty()) {
            assetsAdapter = AssetsAdapter(
                data.products,
                listener = AssetsAdapter.OnClickListener { id, category, description, title ->
                    val bundle = Bundle()
                    bundle.putInt("dataId", id)
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
            assetsAdapter.notifyDataSetChanged()
        } else {
            binding.recyclerViewAssets.visibility = View.GONE
            binding.searchBar.visibility =View.GONE
            binding.noDataImageAssets.visibility = View.VISIBLE
        }
    }

    companion object {
        fun newInstance(): PhotoFragment {
            return PhotoFragment()
        }
    }

}
