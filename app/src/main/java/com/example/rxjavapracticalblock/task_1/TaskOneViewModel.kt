package com.example.rxjavapracticalblock.task_1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavapracticalblock.TAG
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TaskOneViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val mutableLiveData = MutableLiveData<String>()
    val liveData: LiveData<String> = mutableLiveData

    /** Задание 1
     * Сделайте сетевой запрос и отобразите результат на экране? (база)
     */
    init {
        val single = NetworkLoader().getCatFact()

        val disposable = single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mutableLiveData.value = it.fact ?: "Нет данных" },
                { Log.d(TAG, "Error: $it") }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}