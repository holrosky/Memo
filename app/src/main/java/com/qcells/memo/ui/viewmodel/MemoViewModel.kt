package com.qcells.memo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.qcells.memo.MemoApplication
import com.qcells.memo.data.entity.MemoEntity
import com.qcells.memo.ui.repository.MemoRepository
import kotlinx.coroutines.launch

class MemoViewModel(
    private val repository: MemoRepository
) : ViewModel() {

    // MemoListScreen 에서 보여줄 저장된 메모들이 담겨있는 배열
    private val _memos = MutableLiveData(emptyList<MemoEntity>())
    val memos: MutableLiveData<List<MemoEntity>> = _memos

    init {
        initData()
    }

    private fun initData() {
        // 처음 ViewModel이 생성될 때 저장된 메모를 불러온다.
        viewModelScope.launch {
            getAllMemo()
        }

    }

    private suspend fun getAllMemo() {
        _memos.value = repository.getAllMemo()
    }

    fun insertMemo(title: String, content: String) {
        viewModelScope.launch {
            repository.insertMemo(
                MemoEntity(
                    title = title,
                    content = content
                )
            )

            getAllMemo()
        }
    }

    fun updateMemo(id: Int, title: String, content: String) {
        viewModelScope.launch {
            repository.updateMemo(
                MemoEntity(
                    id = id,
                    title = title,
                    content = content
                )
            )

            getAllMemo()
        }
    }

    fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch {
            repository.deleteMemo(memo)
            getAllMemo()
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