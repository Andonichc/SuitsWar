package com.andcch.game.presentation.game

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andcch.coreui.extension.inflate
import com.andcch.game.R
import com.andcch.game.presentation.game.model.PlayerViewModel
import kotlinx.android.synthetic.main.item_player.view.*

class PlayersAdapter :
    ListAdapter<PlayerViewModel, PlayersAdapter.PlayerViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder =
        PlayerViewHolder(parent.inflate(R.layout.item_player))

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.tvTitle
        private val tvBody = itemView.tvBody

        fun bind(player: PlayerViewModel) {
            tvTitle.text = player.name
            tvBody.text = player.points
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<PlayerViewModel>() {

        // This should be comparing ids to check if the content has changed but it's the same item
        // as content won't change and we don't have an id we'll leave it comparing content
        override fun areItemsTheSame(oldItem: PlayerViewModel, newItem: PlayerViewModel): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: PlayerViewModel, newItem: PlayerViewModel): Boolean =
            oldItem == newItem
    }
}
