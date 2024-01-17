package com.qcells.memo.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.qcells.memo.MemoApplication
import com.qcells.memo.data.entity.MemoEntity
import com.qcells.memo.data.reposiroty.MemoRepository
import kotlinx.coroutines.launch

class MemoViewModel(
    private val repository: MemoRepository
) : ViewModel() {

    // MemoListScreen 에서 보여줄 저장된 메모들이 담겨있는 배열
    // 피드백 반영 : 기존에는 Room 데이터베이스로 부터 List<> 형태로 받아왔기 때문에 insert, update 혹은 delete 작업 후
    // 매번 수동으로 getAllMemo() 함수를 호출했지만, 이런 번거로운 호출을 줄이기 위해
    // Room 데이터베이스로부터 LiveData 형태로 받아오게 하였다.
    val memos = repository.getAllMemo()

    fun insertMemo(title: String, content: String) {
        viewModelScope.launch {
            repository.insertMemo(
                MemoEntity(
                    // 피드백 반영 : String 이 비어있다면 다른 내용으로 대체하는 것은 언제든 요구사항에 따라 내용이 바뀔 수
                    // 있는 부분이기 때문에 ViewModel 에서 처리하게 하였다.
                    title = title.ifEmpty { "제목없음" },
                    content = content.ifEmpty { "내용없음" }
                )
            )
        }
    }

    fun updateMemo(id: Int, title: String, content: String) {
        viewModelScope.launch {
            repository.updateMemo(
                MemoEntity(
                    id = id,
                    title = title.ifEmpty { "제목없음" },
                    content = content.ifEmpty { "내용없음" }
                )
            )
        }
    }

    fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch {
            repository.deleteMemo(memo)
        }
    }

    // ViewModel 생성을 위한 Factory. 아래 링크를 참고 하였다.
    // https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-5-pathway-2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-add-repository#5
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // MemoApplication 을 찾기 위해 ViewModelProvider.AndroidViewModelFactory.Companion의 APPLICATION_KEY가 사용되었다.
                MemoViewModel((this[APPLICATION_KEY] as MemoApplication).repository)
            }
        }
    }
}