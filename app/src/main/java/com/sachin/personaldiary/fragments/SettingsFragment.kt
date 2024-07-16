package com.sachin.personaldiary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.sachin.personaldiary.R
import com.sachin.personaldiary.viewmodels.LoginViewModel

class SettingsFragment : Fragment() {

    private lateinit var logoutBtn: ExtendedFloatingActionButton
    private lateinit var updateUsernameBtn: Button
    private lateinit var updatePasswordBtn: Button
    private lateinit var newUsernameEditText: EditText
    private lateinit var oldPasswordEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var oldPassword: String
    private lateinit var newPassword: String

    private lateinit var loginViewModel: LoginViewModel
    private var username: String? = null

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
        updatePasswordBtn = view.findViewById(R.id.buttonUpdatePassword)
        newUsernameEditText = view.findViewById(R.id.editTextNewUsername)
        oldPasswordEditText = view.findViewById(R.id.editTextOldPassword)
        newPasswordEditText = view.findViewById(R.id.editTextNewPassword)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        arguments?.let {
            username = it.getString("USERNAME_KEY")
        }

        logoutBtn.setOnClickListener {
            replaceFragment(LoginFragment())
        }

        updateUsernameBtn.setOnClickListener {
            val newUsername = newUsernameEditText.text.toString().trim()
            if (newUsername.isNotEmpty() && !username.isNullOrEmpty()) {
                if (validateNewUsername(newUsername, username.toString())) {
                    loginViewModel.updateUsername(requireContext(), username!!, newUsername)
                    username = newUsername
                    newUsernameEditText.text = null
                    snackBarMessage("Username updated to $newUsername. Login again with new Username.")
                    replaceFragment(LoginFragment())
                }
            } else {
                newUsernameEditText.error = "New Username can't be empty"
            }
        }

        updatePasswordBtn.setOnClickListener {
            oldPassword = oldPasswordEditText.text.toString().trim()
            newPassword = newPasswordEditText.text.toString().trim()
            if (oldPassword.isNotEmpty() && !username.isNullOrEmpty()) {
                loginViewModel.getLoginDetails(requireContext(), username.toString())!!.observe(viewLifecycleOwner, Observer { loginDetails ->
                    if (loginDetails != null) {
                        if (loginDetails.Password == oldPassword) {
                            if (validateNewPassword(newPasswordEditText)) {
                                loginViewModel.updatePassword(requireContext(), username!!, newPassword)
                                oldPasswordEditText.text = null
                                newPasswordEditText.text = null
                                snackBarMessage("Password updated. Login again with new Password.")
                                replaceFragment(LoginFragment())
                            }
                        } else {
                            oldPasswordEditText.error = "Wrong password!"
                        }
                    } else {
                        snackBarMessage("User not found.")
                    }
                })
            } else {
                oldPasswordEditText.error = "Enter current password"
            }
        }
    }

    private fun validateNewUsername(newUsername: String, oldUserName: String): Boolean {
        var isValid = true
        if (newUsername.isEmpty()) {
            newUsernameEditText.error = "Username can't be empty"
            isValid = false
        } else if (newUsername == oldUserName) {
            newUsernameEditText.error = "Same as previous username"
            isValid = false
        } else if (newUsername.length < 4) {
            newUsernameEditText.error = "Username must be at least 4 characters"
            isValid = false
        }
        return isValid
    }

    private fun validateNewPassword(newPasswordEditText: EditText): Boolean {
        var isValid = true
        if (newPassword.isEmpty()) {
            newPasswordEditText.error = "Enter new password"
            isValid = false
        } else if (newPassword == oldPassword) {
            newPasswordEditText.error = "Same as previous password"
            isValid = false
        } else if (newPassword.length < 6) {
            newPasswordEditText.error = "Password must be at least 6 characters"
            isValid = false
        } else if (!newPassword.any { it.isDigit() }) {
            newPasswordEditText.error = "Password must contain at least one numeric character"
            isValid = false
        } else if (!newPassword.any { it.isLetter() }) {
            newPasswordEditText.error = "Password must contain at least one letter"
            isValid = false
        }
        return isValid
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
