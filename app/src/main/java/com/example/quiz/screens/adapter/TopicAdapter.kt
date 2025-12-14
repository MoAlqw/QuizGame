package com.example.quiz.screens.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.databinding.TopicItemBinding
import com.example.quiz.model.TopicUi

class TopicAdapter(
    private val onClick: (TopicUi) -> Unit
): ListAdapter<TopicUi, TopicAdapter.TopicViewHolder>(DiffCallback) {

    class TopicViewHolder(private val binding: TopicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(topic: TopicUi, onClick: (TopicUi) -> Unit) {
            binding.imageOfTopic.setImageResource(topic.iconRes)
            binding.textViewNameOfTopic.text = topic.name
            binding.textViewCountQuestionsOfTopic.text = "${topic.questionsCount} Вопросов"

            binding.root.setOnClickListener { onClick(topic) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = TopicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic, onClick)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<TopicUi>() {
            override fun areItemsTheSame(old: TopicUi, new: TopicUi) =
                old.id == new.id

            override fun areContentsTheSame(old: TopicUi, new: TopicUi) =
                old == new
        }
    }
}