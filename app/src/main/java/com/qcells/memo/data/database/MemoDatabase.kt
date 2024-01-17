package com.qcells.memo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qcells.memo.data.dao.MemoDao
import com.qcells.memo.data.entity.MemoEntity

// 현재 과제에서는 Entity 가 MemoEntity 하나이므로 MemoEntity::class 하나만 전달한다.
// 버전은 최초 버전인 1로 설정한다.
@Database(entities = arrayOf(MemoEntity::class), version = 1)
abstract class MemoDatabase : RoomDatabase() {
    // 데이터베이스 조작을 위한 Data access object
    abstract fun memoDao(): MemoDao

    // https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7 를 참고하였다.
    companion object {
        @Volatile
        private var INSTANCE: MemoDatabase? = null

        fun getDatabase(context: Context): MemoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instant = Room.databaseBuilder(
                    context,
                    MemoDatabase::class.java,
                    "memo_database"
                ).build()

                INSTANCE = instant
                instant
            }
        }
    }
}