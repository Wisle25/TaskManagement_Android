package com.example.taskmanagement_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.taskmanagement_android.R

class AddTaskFragment : Fragment() {

    private lateinit var inputPriority: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        // Initialize AutoCompleteTextView and set up the adapter
        inputPriority = view.findViewById(R.id.input_priority)
        val items = listOf("High", "Low", "Urgent")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_priority, items)
        inputPriority.setAdapter(adapter)

        // Set up item click listener
        inputPriority.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position).toString()
            Toast.makeText(requireContext(), "Priority: $item", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
