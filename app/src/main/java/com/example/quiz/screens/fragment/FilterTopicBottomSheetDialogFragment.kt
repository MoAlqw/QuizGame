package com.example.quiz.screens.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.quiz.R
import com.example.quiz.viewmodel.TopicsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterTopicBottomSheetDialogFragment:
    BottomSheetDialogFragment(R.layout.bottom_sheet_topic_filter) {

    private val viewModel: TopicsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.topicOsi).setOnClickListener {
            viewModel.applyTopicFilter(TOPIC_OSI)
            dismiss()
        }

        view.findViewById<View>(R.id.topicTcp).setOnClickListener {
            viewModel.applyTopicFilter(TOPIC_TCP)
            dismiss()
        }

        view.findViewById<View>(R.id.topicProtocols).setOnClickListener {
            viewModel.applyTopicFilter(TOPIC_PROTOCOLS)
            dismiss()
        }

        view.findViewById<View>(R.id.buttonReset).setOnClickListener {
            viewModel.resetFilter()
            dismiss()
        }
    }

    companion object {
        const val TOPIC_OSI = 1
        const val TOPIC_TCP = 2
        const val TOPIC_PROTOCOLS = 3

        const val TAG = "FilterTopicBottomSheetDialogFragment"
    }
}