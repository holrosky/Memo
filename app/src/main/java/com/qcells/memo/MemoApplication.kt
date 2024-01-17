package com.qcells.memo

import android.app.Application
import com.qcells.memo.data.database.MemoDatabase
import com.qcells.memo.data.reposiroty.MemoRepositoryImpl

// Application 은 안드로이드 앱이 실행될 때 가장 먼저 생성이 되는 클래스이다.
// ApplicationContext는 앱 전역에서 사용되는 Context 이다. 따라서 앱이 실행되는 동안 단 한개만 존재해야 하는 객체들,
// 보통 데이터 관련 객체들 (Retrofit, Room) 은 ApplicationContext 를 이용하여 싱글턴으로 생성할 수 있따.
// 하지만 위 같은 이유로 Memory Leak 에도 조심해야 할 것.
class MemoApplication : Application() {
    // 싱글턴으로 database와 repository 생성. lazy 키워드로 객체가 사용될 때 생성을 한다.
    // 만약 lazy 를 안쓰고 프로젝트가 확장되어 수많은 객체가 싱글턴으로 한 번에 실행된다면 앱 성능에 영향을 줄 수 있다.
    // lazy 를 사용하면 그 때마다 필요한 객체를 생성할 수 있다.
    private val database by lazy { MemoDatabase.getDatabase(applicationContext) }
    val repository by lazy { MemoRepositoryImpl(database.memoDao()) }
}