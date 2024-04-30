package com.example.nbc__room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Student::class],
    exportSchema = false,
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getMyDao() : MyDAO

    companion object {
        private var INSTANCE: MyDatabase? = null
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {

            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE student_table ADD COLUMN last_update INTERGER")
            }
        }

        /**
         * 싱그톤 패턴으로 인스턴스 하나만 받아올 수 있는 함수
         * 인스턴스 생성 시 school_database DB 이름으로 반환해주는 함수
         */
        fun getDatabase(context: Context) : MyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, MyDatabase::class.java, "school_database")
                    .addMigrations(MIGRATION_1_2, MIGRATION_1_2)
                    .build()
            }
            return INSTANCE as MyDatabase
        }
    }
}