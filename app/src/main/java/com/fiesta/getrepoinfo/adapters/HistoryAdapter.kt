package com.fiesta.getrepoinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fiesta.getrepoinfo.data.RepoData
import com.fiesta.getrepoinfo.databinding.HistoryItemBinding

class HistoryAdapter(private val itemItemClick: OnItemClickListener) : ListAdapter<RepoData, HistoryAdapter.CommitViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CommitViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(info: RepoData) {
            binding.apply {
                author.text = info.url
                author.setOnClickListener(View.OnClickListener {
                     itemItemClick.onitemItemClick(author.text.toString())
                })
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<RepoData>() {
        override fun areItemsTheSame(oldItem: RepoData, newItem: RepoData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: RepoData, newItem: RepoData) =
            oldItem == newItem
    }
    interface OnItemClickListener {
        fun onitemItemClick(url: String)
    }
}