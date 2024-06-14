package com.example.taskmanagement_android

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.taskmanagement_android.databinding.FragmentAddTaskBinding
import com.example.taskmanagement_android.http.RetrofitInstance
import com.example.taskmanagement_android.model.TaskRequest
import com.example.taskmanagement_android.model.RegisterResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    private var userId: String? = null

    private var dueDate: String = ""
    private var dueTime: String = "00:00:00"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch the user ID from DataStore
        lifecycleScope.launch {
            userId = UserPreferences.getUserId(requireContext()).first()
        }

        // Set up priority dropdown
        val priorities = listOf("High", "Low", "Urgent")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_priority, priorities)
        binding.inputPriority.setAdapter(adapter)

        // Set up date picker
        binding.inputDueDate.setOnClickListener {
            showDatePickerDialog()
        }

        // Handle add task button click
        binding.addTask.setOnClickListener {
            val title = binding.inputTitle.text.toString()
            val description = binding.inputDescription.text.toString()
            val priority = binding.inputPriority.text.toString().lowercase()
            val status = view.findViewById<RadioButton>(binding.rbStatus.checkedRadioButtonId)?.text.toString().lowercase()
            val formattedDueDate = "$dueDate $dueTime"

            if (title.isEmpty() || description.isEmpty() || priority.isEmpty() || dueDate.isEmpty() || status.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                addTask(title, description, priority, status, formattedDueDate, userId!!)
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            dueDate = "$selectedYear-${String.format("%02d", selectedMonth + 1)}-${String.format("%02d", selectedDay)}"
            showTimePickerDialog()
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            dueTime = "${String.format("%02d", selectedHour)}:${String.format("%02d", selectedMinute)}:00"
            binding.inputDueDate.setText("$dueDate $dueTime")
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun addTask(title: String, description: String, priority: String, status: String, dueDate: String, userId: String) {
        val request = TaskRequest(title, description, priority, status, dueDate, userId)

        RetrofitInstance.taskApi.addTask(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    Toast.makeText(requireContext(), response.body()?.message ?: "Successfully added task", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.taskListFragment)
                } else {
                    Toast.makeText(requireContext(), response.body()?.message ?: "Add task failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
