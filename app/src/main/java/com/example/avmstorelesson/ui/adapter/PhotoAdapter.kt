package com.example.avmstorelesson.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.avmstorelesson.R
import com.example.avmstorelesson.data.model.Photo
import com.example.avmstorelesson.databinding.StorePhotoListItemBinding

class PhotoAdapter(private val context: Context, private val photos:List<Photo>, val onClick:(photo: Photo)->Unit):
    RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {


    class MyViewHolder(binding: StorePhotoListItemBinding): RecyclerView.ViewHolder(binding.root){
        val ivStore = binding.ivStore
        val ivSelected = binding.ivSelected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            StorePhotoListItemBinding.inflate(LayoutInflater.from(context),parent, false)
        )
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(photos[position]){
            holder.ivSelected.isVisible = isSelected
            holder.ivStore.load(imageUrl){
             placeholder(R.drawable.baseline_image_24)
             error(R.drawable.baseline_cloud_off_24)
            }

            holder.itemView.setOnClickListener {
                onClick(this)
            }
        }
    }
}