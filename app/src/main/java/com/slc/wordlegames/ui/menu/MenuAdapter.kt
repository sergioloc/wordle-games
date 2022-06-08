package com.slc.wordlegames.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.slc.wordlegames.R
import com.slc.wordlegames.domain.model.Game

class MenuAdapter(private var games: List<Game>, private val listener: OnGameClickListener, private val showOptions: Boolean): RecyclerView.Adapter<MenuAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val g = games[position]

        holder.tvName.text = g.name
        holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(holder.ivIcon.context, g.image))

        when (g.status) {
            true -> holder.ivIcon.background = ContextCompat.getDrawable(holder.ivIcon.context, R.color.green)
            false -> holder.ivIcon.background = ContextCompat.getDrawable(holder.ivIcon.context, R.color.red)
            else -> holder.ivIcon.background = ContextCompat.getDrawable(holder.ivIcon.context, R.color.gray)
        }

        //TODO: on single click
        holder.root.setOnClickListener {
            listener.onClickGame(g)
        }

        //TODO: on single click
        holder.ivMore.setOnClickListener {
            listener.onClickOptions(g)
        }

        if (showOptions)
            holder.ivMore.visibility = View.VISIBLE
        else
            holder.ivMore.visibility = View.GONE

    }

    class UserViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.cell_menu, parent, false)) {

        var root: ConstraintLayout = itemView.findViewById(R.id.card)
        var ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var ivMore: ImageView = itemView.findViewById(R.id.ivMore)

    }

    interface OnGameClickListener {
        fun onClickGame(game: Game)
        fun onClickOptions(game: Game)
    }

}