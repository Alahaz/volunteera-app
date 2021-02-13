package com.example.volunteera

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.volunteera.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "LoginFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            login(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString()
            )
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_startFragment)
        }
        binding.btnVerify.setOnClickListener { verify() }
        auth = Firebase.auth
    }

    private fun verify() {
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        requireActivity(),
                        "Verification email sent to ${user.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Failed to send verification email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun login(email: String, password: String) {
        Log.d(TAG, "login account: $email")
        if (!validateForm()) {
            return
        }
        showProgressBar()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(Activity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signinwithemail : success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "signinwithemail : failure", task.exception)
                    Toast.makeText(requireActivity(), "Authentication failed.", Toast.LENGTH_SHORT)
                        .show()
                    updateUI(null)
                }
                hideProgressBar()
            }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressBar()
        if (user != null) {
            if (user.isEmailVerified) {
                findNavController().apply {
                    navigate(R.id.action_loginFragment_to_homeFragment)
                }
            } else {
                binding.btnVerify.visibility = View.VISIBLE
            }
        } else {
            binding.btnVerify.visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = binding.tiEmail.editText?.toString()
        if (TextUtils.isEmpty(email)) {
            binding.tiEmail.error = "Required"
            valid = false
        } else {
            binding.tiEmail.error = null
        }
        val password = binding.tiPassword.editText?.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.tiPassword.error = "Required."
            valid = false
        } else {
            binding.tiPassword.error = null
        }
        return valid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}