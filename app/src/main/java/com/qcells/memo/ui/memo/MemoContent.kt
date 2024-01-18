package com.qcells.memo.ui.memo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qcells.memo.ui.component.CustomTextButton
import com.qcells.memo.ui.component.CustomTextFieldWithLabel

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

        CustomTextButton(
            buttonText = "저장",
            onClick = {
                onSaveClick()
            }
        )

        CustomTextFieldWithLabel(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            label = "제목",
            onValueChange = {
                onTitleValueChange(it)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextFieldWithLabel(
            modifier = Modifier.fillMaxWidth(),
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