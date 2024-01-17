package com.qcells.memo.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun AddMemoScreen(
    onAddClick: (String, String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    val titleText = rememberSaveable { mutableStateOf("") }
    val contentText = rememberSaveable { mutableStateOf("") }

    MemoContent(
        title = titleText.value,
        content = contentText.value,
        onSaveClick = {
            onAddClick(
                titleText.value.ifEmpty { "제목없음" },
                contentText.value.ifEmpty { "내용없음" }
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