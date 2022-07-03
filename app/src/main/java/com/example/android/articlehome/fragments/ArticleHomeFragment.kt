package com.example.android.articlehome.fragments

import android.os.Bundle
import android.provider.MediaStore.Video
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.android.articlehome.R
import com.example.android.articlehome.api.Network
import com.example.android.articlehome.databinding.FragmentArticalHomeBinding
import com.example.android.articlehome.models.ArticleModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber


class ArticleHomeFragment : Fragment() {

    private var _binding: FragmentArticalHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentArticalHomeBinding.inflate(inflater, container, false)
        addMenuItem()




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