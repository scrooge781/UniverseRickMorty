package com.vtlsh.universerickmorty.presentation.main_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.vtlsh.universerickmorty.data.model.Result
import com.vtlsh.universerickmorty.presentation.MainViewModel
import com.vtlsh.universerickmorty.databinding.FragmentMainListBinding
import com.vtlsh.universerickmorty.presentation.main_list.adapter.ItemRemoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainListFragment : Fragment() {

    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var itemRemoteAdapter: ItemRemoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCharacter()

        setupItemRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.itemsRemoteData.observe(viewLifecycleOwner) { items ->
            itemRemoteAdapter.submitList(items.results)
        }
    }

    private fun setupItemRecyclerView() {

        with(binding.rvItemRemote) {
            itemRemoteAdapter = ItemRemoteAdapter(::clickCallback)
            adapter = itemRemoteAdapter
        }

    }

    private fun clickCallback(result: Result) {
        println("hello")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}