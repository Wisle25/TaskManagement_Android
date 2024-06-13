package com.example.taskmanagement_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.taskmanagement_android.databinding.FragmentLoginBinding
import com.example.taskmanagement_android.http.RetrofitInstance
import com.example.taskmanagement_android.model.LoginRequest
import com.example.taskmanagement_android.model.LoginResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var userPreferences: UserPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        userPreferences = UserPreferences(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val savedUsername = userPreferences.username.first()
            val savedPassword = userPreferences.password.first()
            binding.inputIdentity.setText(savedUsername)
            binding.inputPassword.setText(savedPassword)
        }

        binding.loginBtn.setOnClickListener {
            val identity = binding.inputIdentity.text.toString()
            val password = binding.inputPassword.text.toString()

            if (identity.isNotBlank() && password.isNotBlank()) {
                loginUser(identity, password)
            } else {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginUser(identity: String, password: String) {
        val request = LoginRequest(identity, password)
        RetrofitInstance.api.loginUser(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    Toast.makeText(requireContext(), response.body()?.message ?: "Login successful", Toast.LENGTH_SHORT).show()
                    viewLifecycleOwner.lifecycleScope.launch {
                        userPreferences.setLoggedIn(true)
                        userPreferences.setUserToken(response.body()?.data ?: "")
                        userPreferences.setUsername(identity)
                        userPreferences.setPassword(password)
                    }
                    findNavController().navigate(R.id.action_loginFragment_to_taskListFragment)
                } else {
                    Toast.makeText(requireContext(), response.body()?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun logout() {
        lifecycleScope.launch {
            userPreferences.clearUserData()
            findNavController().navigate(R.id.action_currentGragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
