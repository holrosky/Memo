package com.qcells.memo

import android.app.Application
import com.qcells.memo.data.database.MemoDatabase
import com.qcells.memo.data.reposiroty.MemoRepositoryImpl

// database 객체와 repository 객체는 하나만 존재하는게 좋다고 생각하여 싱글턴으로 생성하였다.
class MemoApplication : Application() {
    private val database by lazy { MemoDatabase.getDatabase(applicationContext) }
    val repository by lazy { MemoRepositoryImpl(database.memoDao()) }
}