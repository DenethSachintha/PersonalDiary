package com.sachin.personaldiary.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sachin.personaldiary.R
import com.sachin.personaldiary.viewmodels.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var signUpNavBtn: Button
    private lateinit var txtUsername: AppCompatEditText
    private lateinit var txtPassword: AppCompatEditText
    private lateinit var lblReadResponse: AppCompatTextView

    private lateinit var strUsername: String
    private lateinit var strPassword: String

    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        txtUsername = view.findViewById(R.id.txtUsername)
        txtPassword = view.findViewById(R.id.txtPassword)
        lblReadResponse = view.findViewById(R.id.lblReadResponse)


        signUpNavBtn = view.findViewById(R.id.signUpNavBtn)
        signUpNavBtn.setOnClickListener {
            replaceFragment(SignUpFragment())
        }

        view.findViewById<Button>(R.id.loginBtn).setOnClickListener {

            strUsername = txtUsername.text.toString().trim()
            strPassword = txtPassword.text.toString().trim()

            loginViewModel.getLoginDetails(requireContext(), strUsername)!!.observe(viewLifecycleOwner, Observer {

                if (it == null) {
                    lblReadResponse.text = "User Not Found"
                    txtUsername.text = null
                    txtPassword.text = null
                }
                else {
                    if(it.Password==strPassword){
                        replaceFragment(MainFragment())
                    }else{
                        lblReadResponse.text = "Wrong Password"
                        txtPassword.text = null
                    }
                }
            })
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
