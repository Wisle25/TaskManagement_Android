package com.example.taskmanagement_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {
    private lateinit var txtRegister: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Enable edge to edge
        requireActivity().enableEdgeToEdge()

//        // Apply window insets listener
//        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // Initialize and set click listener for txtRegister
        txtRegister = view.findViewById(R.id.txtRegister)
        txtRegister.setOnClickListener {
            Log.d("LoginFragment", "Login button clicked")
            startActivity(Intent(requireContext(), RegisterFragment::class.java))
        }

        return view
    }
}
