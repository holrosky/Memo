package com.qcells.memo.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.qcells.memo.data.entity.MemoEntity

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo")
    fun getAllMemo(): LiveData<List<MemoEntity>>

    @Insert
    fun insertMemo(memo: MemoEntity)

    @Update
    fun updateMemo(memo: MemoEntity)

    @Delete
    fun deleteMemo(memo: MemoEntity)
}