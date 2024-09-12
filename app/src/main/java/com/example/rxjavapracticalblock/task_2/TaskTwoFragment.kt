package com.example.rxjavapracticalblock.task_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rxjavapracticalblock.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/** Задача №2
 * Сделайте таймер. TextView которая раз в секунду меняется (timer)
 */
class TaskTwoFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()
    private var timer = 1000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_task_two, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.timer)

        val disposable = Observable
            .interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textView.text = "${timer - it}"
            }

        compositeDisposable.add(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.dispose()
    }
}
