package com.fiesta.getrepoinfo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fiesta.getrepoinfo.data.Info
import com.fiesta.getrepoinfo.data.RepoData
import com.fiesta.getrepoinfo.databinding.CommitItemBinding

class CommitListAdapter() : ListAdapter<Info, CommitListAdapter.CommitViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitViewHolder {
        val binding = CommitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CommitViewHolder(private val binding: CommitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(info: Info) {
            binding.apply {
                author.text = info.commit.committer.name
                message.text = info.message
                sha.text = info.sha
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Info>() {
        override fun areItemsTheSame(oldItem: Info, newItem: Info) =
            oldItem.sha == newItem.sha

        override fun areContentsTheSame(oldItem: Info, newItem: Info) =
            oldItem == newItem
    }
}