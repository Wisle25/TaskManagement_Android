package com.example.taskmanagement_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class DetailTaskFragment : Fragment() {

    private lateinit var backButton: Button
    private lateinit var titleDetailTask: TextView
    private lateinit var descDetailTask: TextView
    private lateinit var edtName: TextInputEditText
    private lateinit var edtDescription: TextInputEditText
    private lateinit var edtDate: TextInputEditText
    private lateinit var edtTime: TextInputEditText
    private lateinit var rbPending: RadioButton
    private lateinit var rbInProgress: RadioButton
    private lateinit var rbDone: RadioButton
    private lateinit var btnUpdateTask: Button
    private lateinit var btnDelete: Button
    private lateinit var btnBackToHome: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_task, container, false)

        backButton = view.findViewById(R.id.back_button)
        titleDetailTask = view.findViewById(R.id.title_detailtask)
//        descDetailTask = view.findViewById(R.id.desc_detailtask)
//        edtName = view.findViewById(R.id.edt_name)
//        edtDescription = view.findViewById(R.id.edt_description)
//        edtDate = view.findViewById(R.id.edt_date)
//        edtTime = view.findViewById(R.id.edt_time)
//        rbPending = view.findViewById(R.id.rb_pending)
//        rbInProgress = view.findViewById(R.id.rb_inprogres)
//        rbDone = view.findViewById(R.id.rb_done)
//        btnUpdateTask = view.findViewById(R.id.btn_update_task)
//        btnDelete = view.findViewById(R.id.btn_delete)
//        btnBackToHome = view.findViewById(R.id.btn_backtohome)

//        backButton.setOnClickListener {
//            // Handle back button click
//            activity?.onBackPressed()
//        }

//        btnUpdateTask.setOnClickListener {
//            // Handle update task
//            val taskName = edtName.text.toString()
//            val taskDescription = edtDescription.text.toString()
//            val taskDate = edtDate.text.toString()
//            val taskTime = edtTime.text.toString()
//            val taskStatus = when {
//                rbPending.isChecked -> "Pending"
//                rbInProgress.isChecked -> "In Progress"
//                rbDone.isChecked -> "Done"
//                else -> ""
//            }
//            // TODO: Update the task in the database or backend
//        }

        btnDelete.setOnClickListener {
            // Handle delete task
            // TODO: Delete the task from the database or backend
        }

        btnBackToHome.setOnClickListener {
            // Handle back to home button click
            // TODO: Navigate to the home screen
        }

        // Optionally, load task details if you are editing an existing task
        // TODO: Load task details from the database or backend and populate the fields

        return view
    }
}
