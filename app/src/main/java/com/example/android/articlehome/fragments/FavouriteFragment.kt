package com.example.android.articlehome.fragments

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.articlehome.R
import com.example.android.articlehome.adapter.ArticlesAdapter
import com.example.android.articlehome.databinding.FragmentFavouriteBinding
import com.example.android.articlehome.viewmodels.ArticlesViewModel
import timber.log.Timber


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FavouriteFragment : Fragment() {
    private lateinit var articlesAdapter: ArticlesAdapter
    private val viewModel: ArticlesViewModel by lazy {
        ViewModelProvider(this)[ArticlesViewModel::class.java]
    }
    private var _binding: FragmentFavouriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        articlesAdapter= ArticlesAdapter(ArticlesAdapter.ArticlesListener {
            findNavController().navigate(FavouriteFragmentDirections.actionSecondFragmentToWebViewFragment(it))
        })
        binding.articlesRecycler.adapter=articlesAdapter
        viewModel.allSavedArticles.observe(viewLifecycleOwner) {
            if (it != null) {
                articlesAdapter.submitList(it)
            } else {
                Timber.v("article list is empty")
            }
        }

        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}