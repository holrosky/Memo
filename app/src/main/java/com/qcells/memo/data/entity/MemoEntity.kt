package com.qcells.memo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// "memo" 테이블이름으로 접근하기 위해 이름을 설정한다.
@Entity(tableName = "memo")
data class MemoEntity(
    // Database 기본키로 id를 지정. autoGenerate = true 통해 Room이 새로운 레코드에 대해 자동으로 key 값을 정한다.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String
)