package com.example.starter.presentation.listscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.starter.base.observer
import com.example.starter.databinding.ItemTextRowBinding

class CustomAdapter : Adapter<CustomAdapter.ViewHolder>() {
    var data: List<String> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onClickListener: ((id: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTextRowBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun setOnClickListener(listener: (id: String) -> Unit) {
        onClickListener = listener
    }

    inner class ViewHolder(private val binding: ItemTextRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.itemTextView.text = text

            itemView.setOnClickListener {
                onClickListener?.invoke(text)
            }
        }
    }
}
