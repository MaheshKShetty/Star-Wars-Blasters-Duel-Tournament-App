package com.mshetty.starwarapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mshetty.starwarapp.R
import com.mshetty.starwarapp.databinding.LayoutRowDetailsBinding
import com.mshetty.starwarapp.model.ItemInfo

class PlayersDetailAdapter(val data:List<ItemInfo?>?,val id:Int) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = LayoutRowDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        data?.get(position)?.let { holder.bindViews(it, id = id) }
    }

    override fun getItemCount(): Int {
      return data?.size ?: 0
    }
}

class PlayerViewHolder(private val holder: LayoutRowDetailsBinding)  : ViewHolder (holder.root) {

    fun bindViews(items :ItemInfo,id:Int) {
        var selectedPlayer:Int? = 0
        if (id ==  items.player1?.id) {
            selectedPlayer = items.player1?.id
        } else {
            selectedPlayer = items?.player2?.id
        }
        if (selectedPlayer ==  items.player1?.id) {
             if ((items.player1?.score ?:0) > (items.player2?.score ?:0) ) {
                 holder.container.setBackgroundColor(holder.root.context.getColor(R.color.green))
             } else if ((items.player1?.score ?:0) < (items.player2?.score ?:0)) {
                 holder.container.setBackgroundColor(holder.root.context.getColor(R.color.red))
             } else {
                holder.container.setBackgroundColor(holder.root.context.getColor(R.color.white))
            }
        } else {
            if ((items.player2?.score ?:0) > (items.player1?.score ?:0) ) {
                holder.container.setBackgroundColor(holder.root.context.getColor(R.color.green))
            } else if ((items.player2?.score ?:0) < (items.player1?.score ?:0)) {
                holder.container.setBackgroundColor(holder.root.context.getColor(R.color.red))
            } else {
                holder.container.setBackgroundColor(holder.root.context.getColor(R.color.white))
            }
        }

        holder.tvPoints.text = "${items.player1?.score} - " +items.player2?.score
        holder.tvPlayerName1.text = items.player1?.name
        holder.tvPlayerName2.text = items.player2?.name
    }
}

