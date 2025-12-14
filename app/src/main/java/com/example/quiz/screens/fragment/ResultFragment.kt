package com.example.quiz.screens.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quiz.R
import com.example.quiz.databinding.FragmentResultBinding
import com.example.quiz.screens.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        score = arguments?.getInt(SCORE)!!
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewScore.text = "$score/15"

        binding.buttonBackHome.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, TopicsFragment.newInstance())
                .commit()
        }
    }

    companion object {
        private const val SCORE = "score"

        fun newInstance(score: Int) = ResultFragment().apply {
            arguments = Bundle().apply {
                putInt(SCORE, score)
            }
        }

    }
}