package com.example.android.articlehome.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android.articlehome.databinding.FragmentWebBinding
import com.example.android.articlehome.viewmodels.ArticlesViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WebViewFragment : Fragment() {
    private var _binding: FragmentWebBinding? = null
    private val viewModel: ArticlesViewModel by lazy {
        ViewModelProvider(this)[ArticlesViewModel::class.java]
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWebBinding.inflate(inflater, container, false)
        val article = WebViewFragmentArgs.fromBundle(requireArguments()).selectedArticle
        binding.webView.loadUrl(article.webUrl)
        binding.floatingActionButton.setOnClickListener{
            viewModel.saveFavouriteArticle(article)
        }


        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}