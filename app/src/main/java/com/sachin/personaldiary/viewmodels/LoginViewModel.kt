package com.sachin.personaldiary.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sachin.personaldiary.models.LoginTableModel
import com.sachin.personaldiary.repository.LoginRepository
class LoginViewModel : ViewModel() {

    var liveDataLogin: LiveData<LoginTableModel>? = null

    fun insertData(context: Context, username: String, password: String) {
        LoginRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String): LiveData<LoginTableModel>? {
        liveDataLogin = LoginRepository.getLoginDetails(context, username)
        return liveDataLogin
    }

    fun updateUsername(context: Context, currentUsername: String, newUsername: String) {
        LoginRepository.updateUsername(context, currentUsername, newUsername)
    }
}
