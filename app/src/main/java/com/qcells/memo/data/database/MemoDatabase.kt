package com.qcells.memo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qcells.memo.data.dao.MemoDao
import com.qcells.memo.data.entity.MemoEntity

// 사용할 엔터티 목록. 여러개면 arrayOf()안에 여러 엔티티 전달 가능.
// 룸 데이터베이스의 장점 중 하나가 버전 관리가 편하다는 점. 데이터베이스의 스키마가 바뀌면 컴파일 타임에서 알려주고
// Migration을 사용해 데이터 관리를 할 수 있다고 한다.
@Database(entities = arrayOf(MemoEntity::class), version = 1)
abstract class MemoDatabase : RoomDatabase() {
    // 데이터베이스 조작을 위한 Data access object
    abstract fun memoDao(): MemoDao

    // https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7 를 참고하였다.
    companion object {
        // 동기화를 위한 어노테이션. 멀티 코어 환경에서 스레드는 바로 메인 메모리에 저장하기 보다는 가까운 코어 안의 캐시메모리에 저장을하기 때문에
        // 모든 쓰레드가 INSTANCE를 같은 값으로 공유하지 않는다. 하지만 Volatile 을 사용하면 읽고 쓸 때 메인메모리를 사용한다.
        @Volatile
        private var INSTANCE: MemoDatabase? = null

        // 데이터베이스 빌드.
        fun getDatabase(context: Context): MemoDatabase {
            // 데이터베이스를 생성하려는 여러 쓰레드가 동시에 생성을 하려고 할 때 한 번만 생성하기 위해 synchronized 키워드로 락을 건다.
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