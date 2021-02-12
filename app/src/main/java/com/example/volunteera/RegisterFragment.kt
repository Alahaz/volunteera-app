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
import com.example.volunteera.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "RegisterFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            register(
                binding.tiEmail.editText?.text.toString(),
                binding.tiPassword.editText?.text.toString()
            )
        }
        binding.btnCancel.setOnClickListener {
            cancelRegist()
            findNavController().navigate(R.id.action_registerFragment_to_startFragment)
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

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun register(email: String, password: String) {
        Log.d(TAG, "register account :$email")
        if (!validateform()) {
            return
        }

        showProgressBar()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createdUserWithEmail : $email")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.d(TAG, "createdUserWithEmail : Failed")
                    Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun cancelRegist() {
        auth.signOut()
        updateUI(null)
    }

    private fun validateform(): Boolean {
        var valid = true
        val email = binding.tiEmail.editText?.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.tiEmail.error = "Required"
            valid = false
        } else {
            binding.tiPassword.error = null
        }
        return valid
    }


    private fun reload() {
        auth.currentUser?.reload()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.currentUser?.let { updateUI(it) }
                Toast.makeText(context, "Login Succesfull", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressBar()
        if (user != null) {
            if (user.isEmailVerified) {
                binding.btnVerify.visibility = View.GONE
            } else {
                binding.btnVerify.visibility = View.VISIBLE
                binding.tiEmail.visibility = View.GONE
                binding.tiNama.visibility = View.GONE
                binding.tiPassword.visibility = View.GONE
                binding.btnRegister.visibility = View.GONE
                binding.btnCancel.visibility = View.VISIBLE
            }
        }
    }

}
