package com.example.rxjavapracticalblock.task_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavapracticalblock.R
import io.reactivex.rxjava3.subjects.PublishSubject

class RecyclerAdapter(private val list: List<String>, private val publishSubject: PublishSubject<String>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.recycler_item_text)
        val container: FrameLayout = itemView.findViewById(R.id.recycler_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = list[position]
        holder.container.setOnClickListener {
            publishSubject.onNext(position.toString())
        }
    }
}
