package com.qcells.memo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Dao 에서 tableName 으로 접근가능. tableName을 명시하지 않으면 기본적으로 클래스 이름으로 설정된다.
@Entity(tableName = "memo")
data class MemoEntity(
    // Database 기본키로 id를 지정. autoGenerate = true 통해 Room이 새로운 레코드에 대해 자동으로 key 값을 정한다.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String
)