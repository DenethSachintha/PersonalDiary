package com.sachin.personaldiary.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sachin.personaldiary.models.LoginTableModel
@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(loginTableModel: LoginTableModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?): LiveData<LoginTableModel>

    @Query("UPDATE Login SET Username =:newUsername WHERE Username =:currentUsername")
    suspend fun updateUsername(currentUsername: String, newUsername: String)

    @Query("UPDATE Login SET Password =:newPassword WHERE Username =:username")
    suspend fun updatePassword(username: String, newPassword: String)

}
