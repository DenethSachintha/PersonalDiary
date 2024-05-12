package com.sachin.personaldiary.dao

import androidx.room.*
import com.sachin.personaldiary.models.Diary
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("""SELECT * FROM Diary ORDER BY
        CASE WHEN :isAsc = 1 THEN diaryTitle END ASC, 
        CASE WHEN :isAsc = 0 THEN diaryTitle END DESC""")
    fun getDiaryListSortByDiaryTitle(isAsc: Boolean): Flow<List<Diary>>

    @Query("""SELECT * FROM Diary ORDER BY
        CASE WHEN :isAsc = 1 THEN date END ASC, 
        CASE WHEN :isAsc = 0 THEN date END DESC""")
    fun getDiaryListSortByDiaryDate(isAsc: Boolean): Flow<List<Diary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: Diary): Long

    // First way
    @Delete
    suspend fun deleteDiary(diary: Diary): Int

    // Second Way
    @Query("DELETE FROM Diary WHERE diaryId == :diaryId")
    suspend fun deleteDiaryUsingId(diaryId: String): Int

    @Update
    suspend fun updateDiary(diary: Diary): Int

    @Query("UPDATE Diary SET diaryTitle=:title, description = :description WHERE diaryId = :diaryId")
    suspend fun updateDiaryParticularField(diaryId: String, title: String, description: String): Int

    @Query("SELECT * FROM Diary WHERE diaryTitle LIKE :query ORDER BY date DESC")
    fun searchDiaryList(query: String): Flow<List<Diary>>
}
