package com.andcch.game.presentation.game

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andcch.coreui.extension.inflate
import com.andcch.game.R
import com.andcch.game.presentation.game.model.RoundViewModel
import kotlinx.android.synthetic.main.item_round.view.*

class RoundsAdapter :
    ListAdapter<RoundViewModel, RoundsAdapter.RoundViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoundViewHolder =
        RoundViewHolder(parent.inflate(R.layout.item_round))

    override fun onBindViewHolder(holder: RoundViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.tvTitle
        private val tvBody = itemView.tvBody

        fun bind(round: RoundViewModel) {
            tvTitle.text = round.winnerText
            tvBody.text = round.roundText
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<RoundViewModel>() {

        // This should be comparing ids to check if the content has changed but it's the same item
        // as content won't change and we don't have an id we'll leave it comparing content
        override fun areItemsTheSame(oldItem: RoundViewModel, newItem: RoundViewModel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: RoundViewModel, newItem: RoundViewModel): Boolean =
            oldItem == newItem
    }
}
