package com.example.ourside


import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.ourside.databinding.ItemImageBinding
import kotlin.random.Random


class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Pokemon) {
            with(binding) {
                imageView.setImageResource(data.Image)
            }
    }
}