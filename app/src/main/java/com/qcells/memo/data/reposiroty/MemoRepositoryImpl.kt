package com.qcells.memo.data.reposiroty

import com.qcells.memo.data.dao.MemoDao
import com.qcells.memo.data.entity.MemoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoRepositoryImpl(
    private val memoDao: MemoDao
) : MemoRepository {

    // Room 데이터베이스는 따로 설정을 해주지 않으면 데이터 접근을 백그라운드에서만 가능하도록 하기 때문에 백그라운드 스레드로 스위칭 한다.
    // 만약 메인스레드에서 접근하면 아래와 같은 에러가 발생한다.
    // java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.

    override fun getAllMemo() = memoDao.getAllMemo()

    override suspend fun insertMemo(memo: MemoEntity) = withContext(Dispatchers.IO) {
        memoDao.insertMemo(memo)
    }

    override suspend fun updateMemo(memo: MemoEntity) = withContext(Dispatchers.IO) {
        memoDao.updateMemo(memo)
    }

    override suspend fun deleteMemo(memo: MemoEntity) = withContext(Dispatchers.IO) {
        memoDao.deleteMemo(memo)
    }
}