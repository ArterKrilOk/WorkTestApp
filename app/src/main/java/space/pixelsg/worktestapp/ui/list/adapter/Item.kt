package space.pixelsg.worktestapp.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil

data class Item (
    val id: Int,
    val name: String,
    val imageUrl: String
) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }
}