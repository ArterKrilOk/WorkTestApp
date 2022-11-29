package space.pixelsg.worktestapp.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.pixelsg.worktestapp.databinding.ListItemBinding

class ItemViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item, onItemClick: (id: Int) -> Unit) {
        binding.root.setOnClickListener { onItemClick(item.id) }
        binding.nameView.text = item.name
        Glide
            .with(binding.imageView)
            .load(item.imageUrl)
            .centerCrop()
            .into(binding.imageView)
    }
}