package com.example.android.articlehome.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.articlehome.R
import com.example.android.articlehome.databinding.FragmentArticalHomeBinding

class ArticalHomeFragment : Fragment() {

    private var _binding: FragmentArticalHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =  FragmentArticalHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favourite_page ->   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        return true
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}