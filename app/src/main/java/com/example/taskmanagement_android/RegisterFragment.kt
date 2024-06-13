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

class RegisterFragment : Fragment() {
    private lateinit var txtLogin: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        txtLogin = view.findViewById(R.id.txtLogin)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        txtLogin.setOnClickListener {
            Log.d("RegisterFragment", "Login button clicked")
            startActivity(Intent(activity, LoginFragment::class.java))
            // Jika menggunakan Fragment, gunakan `requireActivity()` sebagai pengganti `this`
        }

        // Enable edge-to-edge display
        activity?.enableEdgeToEdge()

        // Set padding for system bars
        view?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}
