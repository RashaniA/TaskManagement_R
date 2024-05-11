package com.example.taskmanagement.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanagement.R
import com.example.taskmanagement.database.TaskEntry
import com.example.taskmanagement.databinding.FragmentAddBinding
import com.example.taskmanagement.viewmodel.TaskViewModel


class AddFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddBinding.inflate(inflater)

        val myAdapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.priorities)

        )

        binding.apply {
            spinner.adapter = myAdapter
            btnAdd.setOnClickListener {
                if (TextUtils.isEmpty((edtTask.text))){
                    Toast.makeText(requireContext(), "It's Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }

            val title_str = edtTask.text.toString()
            val priority = spinner.selectedItemPosition

            val taskEntry = TaskEntry(
                0,
                title_str,
                priority,
                System.currentTimeMillis()
            )

                viewModel.insert(taskEntry)
                Toast.makeText(requireContext(), "Successfully added!" ,Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment2_to_taskFragment2)

        }
    }
        return binding.root
    }


    }
