package com.example.nbc__imagecollector__typea.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room Database에 접근하기 위한 INSTANCE를 생성합니다.
 */
@Database(entities = [KakaoDocuments::class],
    exportSchema = false,
    version = 1
)
abstract class KakaoDatabase : RoomDatabase() {
    abstract fun getKakaoDao() : KakaoDAO

    companion object {
        private var INSTANCE: KakaoDatabase? = null

        fun getDatabase(context: Context) : KakaoDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, KakaoDatabase::class.java, "archive_database")
                    .build()
            }
            return INSTANCE as KakaoDatabase
        }
    }
}