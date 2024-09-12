package com.example.rxjavapracticalblock.task_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavapracticalblock.R
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Сделайте ресайклер. По нажатию на элемент передавайте его позицию во фрагмент.
 * и во фрагменте этот номер отображайте в тосте. (Subject)*/
class TaskThreeFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_task_three, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val publishSubject = PublishSubject.create<String>()
        val disposable = publishSubject
            .subscribe {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        compositeDisposable.add(disposable)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerAdapter(getList(), publishSubject)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
