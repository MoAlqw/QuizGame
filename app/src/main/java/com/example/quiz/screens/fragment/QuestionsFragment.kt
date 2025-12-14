package com.example.quiz.screens.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quiz.R
import com.example.quiz.databinding.FragmentQuestionsBinding
import com.example.quiz.model.AnswerUi
import com.example.quiz.screens.adapter.AnswerAdapter
import com.example.quiz.screens.common.BaseFragment
import com.example.quiz.viewmodel.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionsFragment : BaseFragment<FragmentQuestionsBinding>(
    FragmentQuestionsBinding::inflate
) {
    private val viewModel: QuestionsViewModel by viewModels()
    private lateinit var topicName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topicName = arguments?.getString(TOPIC_NAME)!!
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewNameOfTopic.text = topicName

        val adapter = AnswerAdapter()
        binding.recyclerQuestion.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentQuestion.collect { question ->
                question?.let {
                    binding.textViewCurrentNumberOfQuestion.text = "Вопрос: ${(it.id - 1) % 15 + 1}/15"
                    binding.textViewNameOfQuestion.text = it.question
                    adapter.submitList(it.answers.mapIndexed { index, answerText ->
                        AnswerUi(id = index, text = answerText)
                    })
                    adapter.clearSelection()
                }
            }
        }

        binding.buttonNextQuestion.setOnClickListener {
            if (viewModel.hasNext()) {
                viewModel.moveToNextQuestion(adapter.selectedPosition)
            } else {
                parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main, ResultFragment.newInstance(viewModel.score))
                    .commit()
            }
        }
    }

    companion object {
        const val TOPIC_ID = "topic_id"
        private const val TOPIC_NAME = "topic_name"

        fun newInstance(
            topicId: Int,
            topicName: String
        ) = QuestionsFragment().apply {
            arguments = Bundle().apply {
                putInt(TOPIC_ID, topicId)
                putString(TOPIC_NAME, topicName)
            }
        }
    }
}