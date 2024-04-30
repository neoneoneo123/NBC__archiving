package com.example.nbc__room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * 쿼리를 날라거나 할 때에는 DAO를 사용할 수 있음
 *
 */
@Dao
interface MyDAO {

    /**
     * 학생 데이터를 삽입하는 함수
     * key 충돌이 나면 새로운 데이터로 교체하여 삽입
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    /**
     * 모든 학생 데이터를 가져오는 함수
     */
    @Query("SELECT * FROM student_table")
    fun getAllStudents(): LiveData<List<Student>>

    /**
     * 이름에 맞는 학생 데이터를 가져오는 함수
     */
    @Query("SELECT * FROM student_table WHERE name = :sname") //메소드 인자를 SQL문에서 :을 붙여 사용
    suspend fun getStudentByName(sname: String): List<Student>

    /**
     * 학생 데이터 하나를 삭제하는 함수
     */
    @Delete
    suspend fun deleteStudent(student: Student)
}