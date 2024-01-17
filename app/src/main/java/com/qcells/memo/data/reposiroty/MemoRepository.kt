package com.qcells.memo.data.reposiroty

import androidx.lifecycle.LiveData
import com.qcells.memo.data.entity.MemoEntity

interface MemoRepository {
    fun getAllMemo(): LiveData<List<MemoEntity>>
    suspend fun insertMemo(memo: MemoEntity)

    suspend fun updateMemo(memo: MemoEntity)

    suspend fun deleteMemo(memo: MemoEntity)
}