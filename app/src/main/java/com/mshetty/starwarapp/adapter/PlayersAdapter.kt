package com.mshetty.starwarapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mshetty.starwarapp.databinding.LayoutRowBinding
import com.mshetty.starwarapp.model.DataItem

class PlayersAdapter(private val data:List<DataItem?>?, private val onClickListener: OnClickListener) : RecyclerView.Adapter<PlayersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val binding = LayoutRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlayersViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        data?.get(position)?.let { holder.bindViews(it,onClickListener) }
    }

    override fun getItemCount(): Int {
      return data?.size ?: 0
    }
}

class PlayersViewHolder(private val holder: LayoutRowBinding, private val context: Context)  : ViewHolder (holder.root) {

    fun bindViews(items :DataItem,onClickListener: OnClickListener) {
        holder.tvPlayerName.text = items.name
        holder.tvPoints.text = items?.totalPlay.toString()
        Glide.with(context).load(items.icon).into(holder.ivPlayer)
        itemView.tag = items.id
        holder.root.setOnClickListener {
            onClickListener.onClick(itemView)
        }
    }
}

