package com.example.quiz.screens.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quiz.R
import com.example.quiz.screens.common.BaseFragment
import com.example.quiz.databinding.FragmentTopicBinding
import com.example.quiz.screens.adapter.TopicAdapter
import com.example.quiz.viewmodel.TopicsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class TopicsFragment: BaseFragment<FragmentTopicBinding>(FragmentTopicBinding::inflate) {

    private val viewModel: TopicsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topicsAdapter = TopicAdapter { topic ->
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main,
                    QuestionsFragment.newInstance(topic.id, topic.name)
                )
                .addToBackStack(null)
                .commit()
        }
        binding.recyclerTopics.adapter = topicsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topics.collect { topicsAdapter.submitList(it) }
        }
    }

    companion object {
        fun newInstance() = TopicsFragment()
    }
}