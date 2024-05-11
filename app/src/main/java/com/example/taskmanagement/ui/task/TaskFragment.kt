package com.example.taskmanagement.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanagement.R
import com.example.taskmanagement.databinding.FragmentTaskBinding
import com.example.taskmanagement.viewmodel.TaskViewModel

class TaskFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTaskBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = TaskAdapter()

        viewModel.getAllTask.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.recyclerView.adapter = adapter

        binding.apply {

            floatingActionButton2.setOnClickListener {
                findNavController().navigate(R.id.action_taskFragment2_to_addFragment2)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}
