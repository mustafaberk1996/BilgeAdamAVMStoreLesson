package com.example.avmstorelesson.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.avmstorelesson.R
import com.example.avmstorelesson.data.model.Store
import com.example.avmstorelesson.databinding.StoreListItemBinding

class StoreAdapter(private val context:Context, private val stores:List<Store>, val onClick:(store:Store)->Unit):RecyclerView.Adapter<StoreAdapter.MyViewHolder>() {


    class MyViewHolder(binding: StoreListItemBinding):ViewHolder(binding.root){
        val tvName = binding.tvName
        val tvFloor = binding.tvFloor
        val ivStore = binding.ivStore
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder(
          StoreListItemBinding.inflate(LayoutInflater.from(context),parent, false)
      )
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(stores[position]){
            holder.tvName.text = name
            holder.tvFloor.text = floor
            holder.ivStore.load(image){
                placeholder(R.drawable.beko_logo)
                error(R.drawable.baseline_cloud_off_24)
            }

            holder.itemView.setOnClickListener {
                onClick(this)
            }
        }
    }
}