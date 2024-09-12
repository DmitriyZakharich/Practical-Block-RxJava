package com.example.rxjavapracticalblock.task_4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.rxjavapracticalblock.R
import com.example.rxjavapracticalblock.TAG
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

/** Задача №4
 *
 * Сделайте EditText. При наборе текста выводите в лог
 * содержимое EditText всегда, когда пользователь
 * 3 секунды что-то не вводил (debounce)*/

class TaskFourFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_task_four, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val flowable = Flowable.create({ emitter ->
            val textWatcher = object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emitter.onNext(s.toString())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}
            }

            view.findViewById<EditText>(R.id.editTextText).addTextChangedListener(textWatcher)
        }, BackpressureStrategy.MISSING)

        compositeDisposable.add(
            flowable.debounce(3000L, TimeUnit.MILLISECONDS).subscribe {
                Log.d(TAG, "input: $it")
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}
