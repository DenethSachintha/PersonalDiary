package com.sachin.personaldiary.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.sachin.personaldiary.R
import com.sachin.personaldiary.viewmodels.LoginViewModel

class SignUpFragment : Fragment() {

    private lateinit var loginNavBtn: Button
    private lateinit var createAccountBtn: Button
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var strUsername: String
    private lateinit var strPassword: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginNavBtn = view.findViewById(R.id.loginNavBtn)
        loginNavBtn.setOnClickListener {
            replaceFragment(LoginFragment())
        }

        createAccountBtn = view.findViewById<Button>(R.id.createAccountBtn)
        createAccountBtn.setOnClickListener {
            val usernameEditText = view.findViewById<AppCompatEditText>(R.id.txtUsername)
            val passwordEditText = view.findViewById<AppCompatEditText>(R.id.txtPassword)

            strUsername = usernameEditText.text.toString().trim()
            strPassword = passwordEditText.text.toString().trim()

            if (validateInput(usernameEditText, passwordEditText)) {
                loginViewModel.insertData(requireContext(), strUsername, strPassword)
                snackBarMessage(strUsername)
                // Clear the EditText fields
                usernameEditText.text = null
                passwordEditText.text = null
                replaceFragment(LoginFragment())
            }
        }
    }

    private fun validateInput(usernameEditText: AppCompatEditText, passwordEditText: AppCompatEditText): Boolean {
        var isValid = true

        if (strUsername.isEmpty()) {
            usernameEditText.error = "Username can't be empty"
            isValid = false
        } else if (strUsername.length < 4) {
            usernameEditText.error = "Username must be at least 4 characters"
            isValid = false
        }

        if (strPassword.isEmpty()) {
            passwordEditText.error = "Password can't be empty"
            isValid = false
        } else if (strPassword.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters"
            isValid = false
        } else if (!strPassword.any { it.isDigit() }) {
            passwordEditText.error = "Password must contain at least one numeric character"
            isValid = false
        } else if (!strPassword.any { it.isLetter() }) {
            passwordEditText.error = "Password must contain at least one letter"
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

    private fun snackBarMessage(username: String) {
        view?.let { rootView ->
            val snackBar = Snackbar.make(
                rootView, "$username created successfully",
                Snackbar.LENGTH_LONG
            )
            snackBar.setAction("Login") {
                replaceFragment(LoginFragment())
            }
            snackBar.show()
        }
    }
}
