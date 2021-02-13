package com.example.volunteera

import androidx.appcompat.app.AppCompatActivity

//import android.os.Bundle
//import android.text.TextUtils
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import com.example.volunteera.databinding.ActivityMainBinding
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase

class MainActivityTestAuth : AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//
//    private lateinit var binding: ActivityMainBinding
//
//    companion object {
//        private const val TAG = "EmailPassword"
//        private const val RC_MULTI_FACTOR = 9005
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnSignIn.setOnClickListener(this)
//        binding.btnCreate.setOnClickListener(this)
//        binding.btnVerify.setOnClickListener(this)
//        binding.btnSignOut.setOnClickListener(this)
//
//        auth = Firebase.auth
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            reload()
//        }
//    }
//
//    private fun reload() {
//        auth.currentUser?.reload()?.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                updateUI(auth.currentUser!!)
//                Toast.makeText(this@MainActivity, "Reload Succes", Toast.LENGTH_SHORT).show()
//            } else {
//                Log.e(TAG, "reload", task.exception)
//                Toast.makeText(this@MainActivity, "Reload failed", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun updateUI(currentUser: FirebaseUser?) {
//        if (currentUser != null) {
//            binding.btnSignOut.visibility = View.VISIBLE
//            binding.btnSignIn.visibility = View.GONE
//            binding.btnCreate.visibility = View.GONE
//            if (currentUser.isEmailVerified) {
//                binding.btnVerify.visibility = View.GONE
//            } else {
//                binding.btnVerify.visibility = View.VISIBLE
//            }
//        } else {
//            binding.btnSignIn.visibility = View.VISIBLE
//            binding.btnCreate.visibility = View.VISIBLE
//            binding.btnSignOut.visibility = View.GONE
//        }
//    }
//
//
//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.btnCreate -> {
//                createAccount(binding.etEmail.text.toString(), binding.etPassword.text.toString())
//            }
//            R.id.btnSignIn -> signIn(binding.etEmail.text.toString(), binding.etPassword.text.toString())
//            R.id.btnVerify -> sendEmailVerification()
//            R.id.btnSignOut -> signOut()
//        }
//    }
//
//    private fun signOut() {
//        auth.signOut()
//        updateUI(null)
//    }
//
//    private fun sendEmailVerification() {
//        binding.btnVerify.isEnabled = false
//
//        val user = auth.currentUser!!
//        user.sendEmailVerification()
//            .addOnCompleteListener(this) { task ->
//                binding.btnVerify.isEnabled = true
//                if (task.isSuccessful) {
//                    Toast.makeText(baseContext, "Verification email sent to ${user.email}",
//                        Toast.LENGTH_SHORT).show()
//                } else {
//                    Log.e(TAG, "sendEmailVerification", task.exception)
//                    Toast.makeText(baseContext, "Failed send verification email.", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//    }
//
//    private fun signIn(email: String, password: String) {
//        Log.d(TAG,"signIn:$email")
//        if (!validateForm()){
//            return
//        }
//        auth.signInWithEmailAndPassword(email,password)
//            .addOnCompleteListener(this){task ->
//                if (task.isSuccessful){
//                    Log.d(TAG,"signInWithEmail:Success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                }else{
//                    Log.d(TAG,"signInWithEmail:Failed")
//                    Toast.makeText(baseContext,"Authentication failed",Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                    checkForMultiFacotrFailure(task.exception)
//                }
//
//
//                if(!task.isSuccessful) {
//                    binding.title.setText("Authentication failed")
//                }
//            }
//    }
//
//    private fun checkForMultiFacotrFailure(e: Exception?) {
//
//    }
//
//    private fun createAccount(email: String, password: String) {
//        Log.d(TAG, "createAccount:$email")
//        if (!validateForm()) {
//            return
//        }
//
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Log.d(TAG, "createdUserWithEmail : Success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    Log.d(TAG, "createdUserWithEmail : Failed ")
//                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }
//    }
//
//    private fun validateForm(): Boolean {
//        var valid = true
//
//        val email = binding.etEmail.text.toString()
//        if (TextUtils.isEmpty(email)) {
//            binding.etEmail.error = "Required"
//            valid = false
//        } else {
//            binding.etPassword.error = null
//        }
//        return valid
//    }
}