package com.sachin.personaldiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sachin.personaldiary.converters.TypeConverter
import com.sachin.personaldiary.dao.DiaryDao
import com.sachin.personaldiary.models.Diary

@Database(
    entities = [Diary::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class DiaryDatabase : RoomDatabase() {

    abstract val diaryDao: DiaryDao

    companion object {
        @Volatile
        private var INSTANCE: DiaryDatabase? = null

        fun getInstance(context: Context): DiaryDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DiaryDatabase::class.java,
                    "diary_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
