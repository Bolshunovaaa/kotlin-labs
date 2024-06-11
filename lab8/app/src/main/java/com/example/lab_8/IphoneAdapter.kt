package com.example.lab_8

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab_8.databinding.RecycleViewBinding


class IphoneAdapter (private val items: List<Pair<String, String?>>,
                     private val onItemClick: (position: Int) -> Unit):
                    RecyclerView.Adapter<IphoneAdapter.IphoneHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IphoneHolder {
        val binding = RecycleViewBinding.inflate(LayoutInflater.from(parent.context))
        return IphoneHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: IphoneHolder, position: Int) {
        val iphone = items[position]
        holder.bind(iphone.first, iphone.second)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class IphoneHolder(private val binding: RecycleViewBinding,
                       private val onItemClick: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
            init {
                binding.iphoneButton.setOnClickListener {
                    onItemClick(adapterPosition)
                }
            }

            fun bind(iphoneName: String, imageUrl: String?) {
                Glide.with(binding.imageView)
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(binding.imageView.drawable)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_foreground)
                    .into(binding.imageView)
                binding.iphoneButton.text = iphoneName
        }
    }
}