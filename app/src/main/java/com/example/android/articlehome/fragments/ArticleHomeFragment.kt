package com.example.android.articlehome.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.articlehome.R
import com.example.android.articlehome.adapter.ArticlesAdapter
import com.example.android.articlehome.databinding.FragmentArticalHomeBinding
import com.example.android.articlehome.viewmodels.ArticlesViewModel
import timber.log.Timber


class ArticleHomeFragment : Fragment() {

    private var _binding: FragmentArticalHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var articlesAdapter: ArticlesAdapter
    private val viewModel: ArticlesViewModel by lazy {
        ViewModelProvider(this)[ArticlesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentArticalHomeBinding.inflate(inflater, container, false)
        addMenuItem()
        articlesAdapter= ArticlesAdapter(ArticlesAdapter.ArticlesListener {
            findNavController().navigate(ArticleHomeFragmentDirections.actionFirstFragmentToWebViewFragment(it))

        })
        binding.articlesRecycler.adapter=articlesAdapter
        viewModel.articles.observe(viewLifecycleOwner) {
            if (it != null) {
                articlesAdapter.submitList(it)
            } else {
               Timber.v("article list is empty")
            }
        }
        return binding.root
    }

    private fun addMenuItem() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.option, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.favourite_page -> findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}