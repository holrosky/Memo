package com.qcells.memo.ui.memo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qcells.memo.data.entity.MemoEntity

/**
 * 저장된 메모들의 리스트와 추가 버튼이 있는 화면.
 * 메모를 클릭하면 해당 객체를 onItemClick() 콜백으로 전달한다.
 * 메모에 있는 삭제 버튼을 누르면 해당 객체를 onDeleteClick() 콜백으로 전달한다.
 * 추가 버튼을 누르면 onAddClick() 을 호출한다.
 **/
@Composable
fun MemoListScreen(
    memos: List<MemoEntity>,
    onItemClick: (MemoEntity) -> Unit,
    onAddClick: () -> Unit,
    onDeleteClick: (MemoEntity) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.End,
    ) {

        // 메모 추가 버튼
        AddButton(
            modifier = Modifier.padding(vertical = 10.dp),
            onClick = {
                onAddClick()
            }
        )

        // 저장된 메모 리스트
        MemoList(
            modifier = Modifier.fillMaxSize(),
            memos = memos,
            onClick = { memo ->
                onItemClick(memo)
            },
            onDeleteClick = { memo ->
                onDeleteClick(memo)
            },
        )
    }
}

@Composable
fun AddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier
            .size(40.dp)
            .clickable {
                onClick()
            },
        imageVector = Icons.Outlined.Add, contentDescription = ""
    )
}

@Composable
fun MemoList(
    memos: List<MemoEntity>,
    onClick: (MemoEntity) -> Unit,
    onDeleteClick: (MemoEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(memos) { index, memo ->
            MemoItem(
                modifier = Modifier.fillMaxWidth(),
                memo = memo,
                onClick = {
                    onClick(it)
                },
                onDeleteClick = {
                    onDeleteClick(it)
                }
            )

            if (index != memos.size - 1)
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp)
                        .height(2.dp)
                )
        }
    }
}

@Composable
fun MemoItem(
    memo: MemoEntity,
    onClick: (MemoEntity) -> Unit,
    onDeleteClick: (MemoEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable {
                onClick(memo)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = memo.title,
                fontSize = 20.sp,
                maxLines = 1
            )

            Text(
                text = memo.content,
                fontSize = 14.sp,
                maxLines = 1
            )
        }
        IconButton(
            onClick = {
                onDeleteClick(memo)
            },

            ) {
            Icon(imageVector = Icons.Outlined.Delete, contentDescription = "")
        }
    }

}