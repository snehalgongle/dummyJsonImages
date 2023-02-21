package com.snehal.android.cryptocurrencychallenge.ui.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snehal.android.cryptocurrencychallenge.data.model.PhotosData
import com.snehal.android.cryptocurrencychallenge.databinding.AssetItemBinding
import com.squareup.picasso.Picasso

class AssetsAdapter(private val photoList: List<PhotosData>, private val listener: OnClickListener) :
        RecyclerView.Adapter<AssetItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AssetItemViewHolder(
                AssetItemBinding.inflate(
                        inflater,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: AssetItemViewHolder, position: Int) {
        holder.bind(photoList[position], listener = listener)
    }

    override fun getItemCount() = photoList.size

    class OnClickListener(val clickListener: (id: Int,albumId:Int,url:String,title:String) -> Unit) {
        fun onClick(id: Int,albumId:Int, url: String, title:String) = clickListener(id,albumId,url,title)
    }
}

class AssetItemViewHolder(private val binding: AssetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    fun bind(photosData: PhotosData, listener: AssetsAdapter.OnClickListener) {
        binding.textViewTitle.text = photosData.title
        Picasso.get()
            .load(photosData.thumbnailUrl)
            .into(binding.imgPhoto)
        itemView.setOnClickListener {
            listener.onClick(photosData.id,photosData.albumId,photosData.url,photosData.title)
        }
    }
}
