package com.qcells.memo.ui.memo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.qcells.memo.data.entity.MemoEntity

@Composable
fun EditMemoScreen(
    memo: MemoEntity,
    onUpdateClick: (Int, String, String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val titleText = rememberSaveable { mutableStateOf(memo.title) }
    val contentText = rememberSaveable { mutableStateOf(memo.content) }

    MemoContent(
        title = titleText.value,
        content = contentText.value,
        onSaveClick = {
            onUpdateClick(
                memo.id,
                titleText.value,
                contentText.value
            )
        },
        onTitleValueChange = {
            titleText.value = it
        },
        onContentValueChange = {
            contentText.value = it
        },
        onBackButtonClick = {
            onBack()
        }
    )
}