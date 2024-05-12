package com.sachin.personaldiary.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.sachin.personaldiary.database.DiaryDatabase
import com.sachin.personaldiary.models.Diary
import com.sachin.personaldiary.utils.Resource

import com.sachin.personaldiary.utils.Resource.*
import com.sachin.personaldiary.utils.StatusResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.launch


class DiaryRepository(application: Application) {

    private val diaryDao = DiaryDatabase.getInstance(application).diaryDao

    private val _diaryStateFlow = MutableStateFlow<Resource<Flow<List<Diary>>>>(Loading())
    val diaryStateFlow: StateFlow<Resource<Flow<List<Diary>>>>
        get() = _diaryStateFlow

    private val _statusLiveData = MutableLiveData<Resource<StatusResult>>()
    val statusLiveData: LiveData<Resource<StatusResult>>
        get() = _statusLiveData


    private val _sortByLiveData = MutableLiveData<Pair<String,Boolean>>().apply {
        postValue(Pair("date",false))
    }
    val sortByLiveData: LiveData<Pair<String,Boolean>>
        get() = _sortByLiveData


    fun setSortBy(sort:Pair<String,Boolean>){
        _sortByLiveData.postValue(sort)
    }

    fun getDiaryList(isAsc : Boolean, sortByName:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _diaryStateFlow.emit(Loading())
                delay(500)
                val result = if (sortByName == "title"){
                    diaryDao.getDiaryListSortByDiaryTitle(isAsc)
                }else{
                    diaryDao.getDiaryListSortByDiaryDate(isAsc)
                }
                _diaryStateFlow.emit(Success("loading", result))
            } catch (e: Exception) {
                _diaryStateFlow.emit(Error(e.message.toString()))
            }
        }
    }


    fun insertDiary(diary: Diary) {
        try {
            _statusLiveData.postValue(Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = diaryDao.insertDiary(diary)
                handleResult(result.toInt(), "Inserted Diary Successfully", StatusResult.Added)
            }
        } catch (e: Exception) {
            _statusLiveData.postValue(Error(e.message.toString()))
        }
    }


    fun deleteDiary(diary: Diary) {
        try {
            _statusLiveData.postValue(Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = diaryDao.deleteDiary(diary)
                handleResult(result, "Deleted Diary Successfully", StatusResult.Deleted)

            }
        } catch (e: Exception) {
            _statusLiveData.postValue(Error(e.message.toString()))
        }
    }

    fun deleteDiaryUsingId(diaryId: String) {
        try {
            _statusLiveData.postValue(Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = diaryDao.deleteDiaryUsingId(diaryId)
                handleResult(result, "Deleted Diary Successfully", StatusResult.Deleted)

            }
        } catch (e: Exception) {
            _statusLiveData.postValue(Error(e.message.toString()))
        }
    }


    fun updateDiary(diary: Diary) {
        try {
            _statusLiveData.postValue(Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = diaryDao.updateDiary(diary)
                handleResult(result, "Updated Diary Successfully", StatusResult.Updated)

            }
        } catch (e: Exception) {
            _statusLiveData.postValue(Error(e.message.toString()))
        }
    }

    fun updateDiaryParticularField(diaryId: String, title: String, description: String) {
        try {
            _statusLiveData.postValue(Loading())
            CoroutineScope(Dispatchers.IO).launch {
                val result = diaryDao.updateDiaryParticularField(diaryId, title, description)
                handleResult(result, "Updated Diary Successfully", StatusResult.Updated)

            }
        } catch (e: Exception) {
            _statusLiveData.postValue(Error(e.message.toString()))
        }
    }

    fun searchDiaryList(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                _diaryStateFlow.emit(Loading())
                val result = diaryDao.searchDiaryList("%${query}%")
                _diaryStateFlow.emit(Success("loading", result))
            } catch (e: Exception) {
                _diaryStateFlow.emit(Error(e.message.toString()))
            }
        }
    }


    private fun handleResult(result: Int, message: String, statusResult: StatusResult) {
        if (result != -1) {
            _statusLiveData.postValue(Success(message, statusResult))
        } else {
            _statusLiveData.postValue(Error("Something Went Wrong", statusResult))
        }
    }
}
