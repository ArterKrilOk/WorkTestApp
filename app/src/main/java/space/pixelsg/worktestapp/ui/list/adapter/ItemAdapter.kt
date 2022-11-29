package space.pixelsg.worktestapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import space.pixelsg.worktestapp.databinding.ListItemBinding

class ItemAdapter(
    private val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<Item, ItemViewHolder>(Item.DIFF_UTIL) {
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
}