package com.qcells.memo.ui.repository

import com.qcells.memo.data.entity.MemoEntity

interface MemoRepository {
    suspend fun getAllMemo(): List<MemoEntity>

    suspend fun insertMemo(memo: MemoEntity)

    suspend fun updateMemo(memo: MemoEntity)

    suspend fun deleteMemo(memo: MemoEntity)
}