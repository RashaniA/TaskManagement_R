package com.example.taskmanagement.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagement.database.TaskEntry
import com.example.taskmanagement.databinding.RowLayoutBinding

class TaskAdapter(private val clickListener: (TaskEntry) -> Unit) : ListAdapter<TaskEntry, TaskAdapter.ViewHolder>(TaskDiffCallback) {

    companion object TaskDiffCallback : DiffUtil.ItemCallback<TaskEntry>() {
        override fun areItemsTheSame(oldItem: TaskEntry, newItem: TaskEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskEntry, newItem: TaskEntry): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskEntry: TaskEntry, clickListener: (TaskEntry) -> Unit) {
            binding.taskEntry = taskEntry
            binding.root.setOnClickListener { clickListener(taskEntry) }
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }
}


class TaskClickListener(val clickListener: (TaskEntry) -> Unit) {
    fun onClick(taskEntry: TaskEntry) = clickListener(taskEntry)
}


