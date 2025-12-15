package com.example.quiz.screens.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.quiz.R
import com.example.quiz.screens.common.BaseFragment
import com.example.quiz.databinding.FragmentTopicBinding
import com.example.quiz.model.toUi
import com.example.quiz.screens.adapter.TopicAdapter
import com.example.quiz.viewmodel.AuthViewModel
import com.example.quiz.viewmodel.TopicsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class TopicsFragment: BaseFragment<FragmentTopicBinding>(FragmentTopicBinding::inflate) {

    private val viewModel: TopicsViewModel by activityViewModels()
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var topicAdapter: TopicAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filteredTopics.collect { topics ->
                topicAdapter.submitList(topics.map { it.toUi() })
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            authViewModel.authResult.collect { result ->
                result?.onSuccess {
                    binding.textViewNameOfUser.text = it.email
                }
            }
        }

        binding.editTextSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.onSearchQueryChanged(text.toString())
        }

        binding.root.setOnClickListener {
            hideKeyboardAndClearFocus()
        }

        binding.buttonFilter.setOnClickListener {
            FilterTopicBottomSheetDialogFragment()
                .show(parentFragmentManager, FilterTopicBottomSheetDialogFragment.TAG)
        }
    }

    private fun initAdapter() {
        topicAdapter = TopicAdapter { topic ->
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main,
                    QuestionsFragment.newInstance(topic.id, topic.name)
                )
                .addToBackStack(null)
                .commit()
        }

        binding.recyclerTopics.adapter = topicAdapter
    }

    private fun hideKeyboardAndClearFocus() {
        val imm = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        binding.editTextSearch.clearFocus()
    }

    companion object {
        fun newInstance() = TopicsFragment()
    }
}