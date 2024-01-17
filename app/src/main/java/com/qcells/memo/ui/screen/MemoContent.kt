package com.qcells.memo.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MemoContent(
    title: String,
    content: String,
    onSaveClick: () -> Unit,
    onTitleValueChange: (String) -> Unit,
    onContentValueChange: (String) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.End,
    ) {

        SaveMemoButton(
            onClick = {
                onSaveClick()
            }
        )

        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            label = "제목",
            onValueChange = {
                onTitleValueChange(it)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            modifier = Modifier.fillMaxSize(),
            text = content,
            label = "내용",
            onValueChange = {
                onContentValueChange(it)
            }
        )
    }

    BackHandler(
        onBack = onBackButtonClick
    )
}

@Composable
fun SaveMemoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        modifier = modifier,
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = "저장"
        )
    }
}

@Composable
fun CustomTextField(
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        label = { Text(label) },
        onValueChange = onValueChange
    )
}