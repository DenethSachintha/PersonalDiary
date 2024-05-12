package com.sachin.personaldiary.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sachin.personaldiary.models.Diary
import com.sachin.personaldiary.repository.DiaryRepository
import com.sachin.personaldiary.utils.Resource

class DiaryViewModel(application: Application) : AndroidViewModel(application) {

    private val diaryRepository = DiaryRepository(application)
    val diaryStateFlow get() =  diaryRepository.diaryStateFlow
    val statusLiveData get() =  diaryRepository.statusLiveData
    val sortByLiveData get() =  diaryRepository.sortByLiveData

    fun setSortBy(sort:Pair<String,Boolean>){
        diaryRepository.setSortBy(sort)
    }
    fun getDiaryList(isAsc : Boolean, sortByName:String) {
        diaryRepository.getDiaryList(isAsc, sortByName)
    }

    fun insertDiary(diary: Diary){
        diaryRepository.insertDiary(diary)
    }

    fun deleteDiary(diary: Diary) {
        diaryRepository.deleteDiary(diary)
    }

    fun deleteDiaryUsingId(diaryId: String){
        diaryRepository.deleteDiaryUsingId(diaryId)
    }

    fun updateDiary(diary: Diary) {
        diaryRepository.updateDiary(diary)
    }

    fun updateDiaryParticularField(diaryId: String,title:String,description:String) {
        diaryRepository.updateDiaryParticularField(diaryId, title, description)
    }

    fun searchDiaryList(query: String){
        diaryRepository.searchDiaryList(query)
    }
}
