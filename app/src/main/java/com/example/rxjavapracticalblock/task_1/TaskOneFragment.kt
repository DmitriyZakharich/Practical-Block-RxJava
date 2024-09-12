package com.example.rxjavapracticalblock.task_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.rxjavapracticalblock.R

/** Задание 1
 * Сделайте сетевой запрос и отобразите результат на экране? (база)
 */

class TaskOneFragment : Fragment() {

    private val viewModel: TaskOneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_task_one, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiTextView = view.findViewById<TextView>(R.id.api_textview)
        viewModel.liveData.observe(viewLifecycleOwner){
            apiTextView.text = it
        }
    }
}
