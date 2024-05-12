package com.example.taskmanagement.ui.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanagement.R
import com.example.taskmanagement.database.TaskEntry
import com.example.taskmanagement.databinding.FragmentUpdateBinding
import com.example.taskmanagement.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUpdateBinding.inflate(inflater)

        // Retrieve arguments from the bundle
        val taskEntry = arguments?.getParcelable<TaskEntry>("taskEntry")

        binding.apply {
            // Set data from the taskEntry to UI components
            updateEdtTask.setText(taskEntry?.title)
            updateSpinner.setSelection(taskEntry?.priority ?: 0)

            btnUpdate.setOnClickListener {
                if (TextUtils.isEmpty(updateEdtTask.text)) {
                    Toast.makeText(requireContext(), "It's Empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val task_str = updateEdtTask.text
                val priority = updateSpinner.selectedItemPosition

                // Create a new TaskEntry object with updated data
                val updatedTaskEntry = TaskEntry(
                    taskEntry?.id ?: 0,
                    task_str.toString(),
                    priority,
                    taskEntry?.timestamp ?: System.currentTimeMillis()
                )

                // Update the task using ViewModel
                viewModel.update(updatedTaskEntry)

                // Navigate back to the task list fragment
                findNavController().navigate(R.id.action_updateFragment2_to_taskFragment2)
            }
        }

        return binding.root
    }
}
