package com.example.rxjavapracticalblock.task_5

import android.util.Log
import com.example.rxjavapracticalblock.TAG
import io.reactivex.rxjava3.core.Single

class TestCases {

    private val first = Single.just(listOf("1", "2", "3"))
    private val second = Single.just(listOf("4", "5", "6", "7"))
    private val failure = Single.error<List<String>>(Throwable("error"))

    fun execute() {
        errorResistant(first, second)
            .subscribe({
                Log.d(TAG, "errorResistant $it")
            }, {
                Log.d(TAG, "errorResistant $it")
            })

        errorResistant(first, failure)
            .subscribe({
                Log.d(TAG, "errorResistant $it")
            }, {
                Log.d(TAG, "errorResistant $it")
            })


        notErrorResistant(first, second)
            .subscribe({
                Log.d(TAG, "notErrorResistant $it")
            }, {
                Log.d(TAG, "notErrorResistant $it")
            })

        notErrorResistant(first, failure)
            .subscribe({
                Log.d(TAG, "notErrorResistant $it")
            }, {
                Log.d(TAG, "notErrorResistant $it")
            }
            )
    }
}
