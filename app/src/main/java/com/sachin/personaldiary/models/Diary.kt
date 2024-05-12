package com.sachin.personaldiary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity()
data class Diary(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "diaryId")
    val id: String,
    @ColumnInfo(name = "diaryTitle")
    val title: String,
    val description: String,
    val date: Date,
    @ColumnInfo(name = "imageData", typeAffinity = ColumnInfo.BLOB)
    val imageData : ByteArray
)
