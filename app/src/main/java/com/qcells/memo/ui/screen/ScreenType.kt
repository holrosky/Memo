package com.qcells.memo.ui.screen

import com.qcells.memo.data.entity.MemoEntity

sealed class ScreenType {
    data object MemoListScreen : ScreenType()
    data class EditMemoScreen(val memo: MemoEntity) : ScreenType()
    data object AddMemoScreen : ScreenType()
}