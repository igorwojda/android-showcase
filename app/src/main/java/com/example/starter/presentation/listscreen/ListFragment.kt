package com.example.starter.presentation.listscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starter.R
import com.example.starter.databinding.FragmentListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding()
    private val viewModel: ListViewModel by viewModel()

    private val customAdapter: CustomAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customAdapter.setOnClickListener {
            viewModel.onItemSelected(it)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = customAdapter
        }

        viewModel.navigate.observe(viewLifecycleOwner) {
            findNavController().navigate(it)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            customAdapter.data = it
        }

        viewModel.onEnter()
    }
}
