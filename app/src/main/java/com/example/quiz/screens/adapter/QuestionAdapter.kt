package com.example.quiz.screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.databinding.ItemAnswerBinding
import com.example.quiz.model.AnswerUi

class AnswerAdapter : ListAdapter<AnswerUi, AnswerAdapter.AnswerViewHolder>(DiffCallback) {

    var selectedPosition: Int? = null

    inner class AnswerViewHolder(private val binding: ItemAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(answer: AnswerUi, position: Int) {
            binding.textAnswer.text = answer.text

            val isSelected = selectedPosition == position
            if (isSelected) {
                binding.cardAnswer.setCardBackgroundColor(
                    binding.root.context.getColor(R.color.dark_blue)
                )
                binding.textAnswer.setTextColor(
                    binding.root.context.getColor(R.color.white)
                )
            } else {
                binding.cardAnswer.setCardBackgroundColor(
                    binding.root.context.getColor(R.color.white)
                )
                binding.textAnswer.setTextColor(
                    binding.root.context.getColor(R.color.black)
                )
            }

            binding.cardAnswer.setOnClickListener {
                val previous = selectedPosition
                selectedPosition = position
                notifyItemChanged(previous ?: -1)
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val binding = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = getItem(position)
        holder.bind(answer, position)
    }

    fun clearSelection() {
        val previous = selectedPosition
        selectedPosition = null
        previous?.let { notifyItemChanged(it) }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<AnswerUi>() {
            override fun areItemsTheSame(old: AnswerUi, new: AnswerUi) = old.id == new.id
            override fun areContentsTheSame(old: AnswerUi, new: AnswerUi) = old == new
        }
    }
}