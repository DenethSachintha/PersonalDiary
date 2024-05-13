package com.sachin.personaldiary.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.sachin.personaldiary.R
import com.sachin.personaldiary.databinding.ActivityMainBinding
import com.sachin.personaldiary.viewmodels.LoginViewModel


class SignUpFragment : Fragment() {

    private lateinit var loginNavBtn: Button
    private lateinit var createAccountBtn: Button

    private lateinit var loginViewModel: LoginViewModel


    lateinit var strUsername: String
    lateinit var strPassword: String

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

            if (strUsername.isEmpty()) {
                usernameEditText.error = "Username Can't Be Empty"
            } else if (strPassword.isEmpty()) {
                passwordEditText.error = "Password Can't Be Empty"
            } else {
                loginViewModel.insertData(requireContext(), strUsername, strPassword)
                view.findViewById<AppCompatTextView>(R.id.lblInsertResponse).text = "Inserted Successfully"

                // Clear the EditText fields
                usernameEditText.text = null
                passwordEditText.text = null
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}