package com.svg.dog_generator.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.RequestManager
import com.svg.dog_generator.databinding.DogLayoutBinding
import com.svg.dog_generator.domain.entities.DogResponseModel

class RecentDogAdapter(private val glide: RequestManager): RecyclerView.Adapter<RecentDogAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<DogResponseModel>(){
        override fun areItemsTheSame(oldItem: DogResponseModel, newItem: DogResponseModel): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: DogResponseModel, newItem: DogResponseModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)


    class ViewHolder(val binding: DogLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DogLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            dogImage.load(differ.currentList[position].imageUrl)
        }
    }
}
