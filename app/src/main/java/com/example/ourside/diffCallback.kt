package com.example.ourside

import androidx.recyclerview.widget.DiffUtil

class diffCallback :DiffUtil.ItemCallback<Pokemon>() {

//    두 객체를 비교해 동일한지 비교
   override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}