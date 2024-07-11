package com.sachin.personaldiary.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.sachin.personaldiary.R
import com.sachin.personaldiary.viewmodels.LoginViewModel

class SettingsFragment : Fragment() {

    private lateinit var logoutBtn: ExtendedFloatingActionButton
    private lateinit var updateUsernameBtn: Button
    private lateinit var newUsernameEditText: EditText

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutBtn = view.findViewById(R.id.logout)
        updateUsernameBtn = view.findViewById(R.id.buttonUpdateUsername)
        newUsernameEditText = view.findViewById(R.id.editTextNewUsername)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        arguments?.let {
            username = it.getString("USERNAME_KEY", "")
        }

        logoutBtn.setOnClickListener {
            replaceFragment(LoginFragment())
        }

        updateUsernameBtn.setOnClickListener {
            val newUsername = newUsernameEditText.text.toString().trim()
            if (newUsername.isNotEmpty() && username != null) {
                loginViewModel.updateUsername(requireContext(), username!!, newUsername)
                username = newUsername
                newUsernameEditText.text = null
                snackBarMessage("Username updated to $newUsername")
            } else {
                newUsernameEditText.error = "New Username can't be empty"
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun snackBarMessage(message: String) {
        view?.let { rootView ->
            val snackBar = Snackbar.make(
                rootView, message,
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }
    }
}