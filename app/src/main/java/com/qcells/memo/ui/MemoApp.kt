package com.qcells.memo.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.qcells.memo.ui.screen.AddMemoScreen
import com.qcells.memo.ui.screen.EditMemoScreen
import com.qcells.memo.ui.screen.MemoListScreen
import com.qcells.memo.ui.screen.ScreenType
import com.qcells.memo.ui.viewmodel.MemoViewModel

/**
 * Root Composable 함수.
 * currentScreen 의 ScreenType 에 따라 MemoListScreen, AddMemoScreen 혹은 EditMemoScreen 을 보여준다.
 * AddMemoScreen 은 EditMemoScreen 을 MemoContent Composable 을 공유한다.
 * 원래는 MemoListScreen 과 MemoDetailedScreen 두개만 있었다. 메모를 수정하는 화면과 추가하는 화면이 동일하기 때문에
 * 화면을 재사용 하려고 했지만, 수정과 추가를 구분하기 위해 MemoDetailedScreen 함수의 인자에 memo 객체를 nullable 하게 받았고,
 * 이는 좋은 방법이라 생각하지 않아서 memo 객체를 받지않는 AddMemoScreen 화면과 memo 객체를 받는 EditMemoScreen 으로 나누었다.
 * 대신 그 외 기존 텍스트 필드 등은 MemoContent Composable 함수로 따로 분리하여 AddMemoScreen 과 EditMemoScreen 이
 * 공유할 수 있게 하였다.
 * 각 스크린으로 부터 콜백을 받아 해당 정보를 통해 ViewModel 의 메서드를 호출한다.
**/
@Composable
fun MemoApp() {
    // ViewModel 은 생성자가 비어있지 않으면 Factory 를 전달하여 생성해야 한다.
    // Because the Android framework does not allow a ViewModel to be passed values in the constructor when created, we implement a ViewModelProvider.Factory object, which lets us get around this limitation.

    val viewModel: MemoViewModel = viewModel(factory = MemoViewModel.Factory)

    val memos = viewModel.memos.observeAsState()
    var currentScreen by remember { mutableStateOf<ScreenType>(ScreenType.MemoListScreen) }
    SideEffect {
        Log.e("MemoApp", "RECOMPOSTION!")
    }

    when (currentScreen) {
        is ScreenType.MemoListScreen ->
            MemoListScreen(
                memos = memos.value ?: emptyList(),
                // MemoListScreen 에서 추가 버튼이 눌릴 경우
                onAddClick = {
                    currentScreen = ScreenType.AddMemoScreen
                },
                // MemoListScreen 에서 저장된 메모가 눌릴 경우
                onItemClick = { memo ->
                    currentScreen = ScreenType.EditMemoScreen(memo)
                },
                // MemoListScreen 에서 메모 삭제가 눌릴 경우
                onDeleteClick = { memo ->
                    viewModel.deleteMemo(memo)
                }
            )

        is ScreenType.AddMemoScreen ->
            AddMemoScreen(
                // AddMemoScreen 에서 저장(추가) 버튼이 눌릴 경우
                onAddClick = { title, content ->
                    viewModel.insertMemo(title, content)
                    currentScreen = ScreenType.MemoListScreen
                },
                // AddMemoScreen 백버튼이 눌릴 경우
                onBack = {
                    currentScreen = ScreenType.MemoListScreen
                }
            )

        is ScreenType.EditMemoScreen ->
            EditMemoScreen(
                memo = (currentScreen as ScreenType.EditMemoScreen).memo,
                // EditMemoScreen 에서 저장(수정) 버튼이 눌릴 경우
                onUpdateClick = { id, title, content ->
                    viewModel.updateMemo(id, title, content)
                    currentScreen = ScreenType.MemoListScreen
                },
                // EditMemoScreen 백버튼이 눌릴 경우
                onBack = {
                    currentScreen = ScreenType.MemoListScreen
                }
            )
    }
}


