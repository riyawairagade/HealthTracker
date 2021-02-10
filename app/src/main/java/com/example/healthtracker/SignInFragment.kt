package com.example.healthtracker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.healthtracker.databinding.FragmentSigninBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment() {

    private var auth = Firebase.auth
    private lateinit var binding: FragmentSigninBinding
    private lateinit var _googleSignInClient: GoogleSignInClient
    private val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleSignInResult(task)
                } else {
                    Toast.makeText(activity,"Google sign in failed. Please try again later",Toast.LENGTH_LONG).show();
                }
            }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSigninBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Firebase.auth.currentUser!= null){
            findNavController().navigate(R.id.action_signInFragment_to_healthFragment)
        }

        _googleSignInClient = GoogleSignIn.getClient(
                requireContext(),
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("182451858585-4j2nog9k3emcl3n3mipcj3a03cblsn8c.apps.googleusercontent.com")
                        .requestEmail()
                        .build()
        )

        with(binding) {
            signInButton.setOnClickListener {
                startForResult.launch(_googleSignInClient.signInIntent)
            }

        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        if (completedTask.isSuccessful) {
            val account = completedTask.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!)
        } else {
            Toast.makeText(activity,"Google sign in failed. Please try again later",Toast.LENGTH_LONG).show();
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_signInFragment_to_healthFragment)
                    } else {
                        Toast.makeText(activity,"signInWithCredential:failure",Toast.LENGTH_LONG).show();
                    }
                }

    }

}




