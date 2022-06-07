package com.slc.wordlegames.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.slc.wordlegames.R
import com.slc.wordlegames.domain.model.History

class HistoryAdapter(private var history: List<History>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int = history.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val h = history[position]

        holder.tvDate.text = h.date
        holder.tvResult.text = h.result

        if (h.status) {
            holder.clHeader.background = ContextCompat.getDrawable(holder.clHeader.context, R.drawable.chip_green)
            holder.ivStatus.setImageDrawable(ContextCompat.getDrawable(holder.clHeader.context, R.drawable.ic_check))
        }
        else {
            holder.clHeader.background = ContextCompat.getDrawable(holder.clHeader.context, R.drawable.chip_red)
            holder.ivStatus.setImageDrawable(ContextCompat.getDrawable(holder.clHeader.context, R.drawable.ic_close))
        }

    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.cell_history, parent, false)) {

        var clHeader: ConstraintLayout = itemView.findViewById(R.id.clHeader)
        var ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)
        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var tvResult: TextView = itemView.findViewById(R.id.tvResult)

    }

}