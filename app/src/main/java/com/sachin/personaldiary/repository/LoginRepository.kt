package com.sachin.personaldiary.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sachin.personaldiary.models.LoginTableModel
import com.sachin.personaldiary.database.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LoginRepository {

    companion object {

        var loginDatabase: LoginDatabase? = null
        var loginTableModel: LiveData<LoginTableModel>? = null

        fun initializeDB(context: Context): LoginDatabase {
            return LoginDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {
            loginDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val loginDetails = LoginTableModel(username, password)
                loginDatabase!!.loginDao().InsertData(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, username: String): LiveData<LoginTableModel>? {
            loginDatabase = initializeDB(context)
            loginTableModel = loginDatabase!!.loginDao().getLoginDetails(username)
            return loginTableModel
        }

        fun updateUsername(context: Context, currentUsername: String, newUsername: String) {
            loginDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                loginDatabase!!.loginDao().updateUsername(currentUsername, newUsername)
            }
        }

        fun updatePassword(context: Context, username: String, newPassword: String) {
            loginDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                loginDatabase!!.loginDao().updatePassword(username, newPassword)
            }
        }
    }
}
