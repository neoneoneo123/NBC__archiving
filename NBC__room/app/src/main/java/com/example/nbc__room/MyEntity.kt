package com.example.nbc__room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class MyEntity(
    @PrimaryKey
    @ColumnInfo(name = "student_id") //이 컬럼명으로 데이터베이스 안에 들어갈 것임
    val id: Int,
    val name: String
)
