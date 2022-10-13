package com.example.spacex.launch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.launch.data.model.YearData


class SectionAdapter(context: Context, items: ArrayList<YearData>?) :
    RecyclerView.Adapter<SectionAdapter.TodoSectionViewHolder>() {

    private val items: ArrayList<YearData>?
    private val context: Context

    init {
        this.items = items
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoSectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_section, null)
        view.layoutParams =
            RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        return TodoSectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (items != null) {
            return items.count()
        }

        return 0
    }

    override fun onBindViewHolder(holder: TodoSectionViewHolder, position: Int) {
        val name = items?.get(position)?.yearTitle
        val sections = items?.get(position)?.launchList

        val adapter = sections?.let { SubSectionAdapter(context, it) }


        holder.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration = DividerItemDecoration(
            context,
            LinearLayoutManager.VERTICAL
        )

        holder.recyclerView.addItemDecoration(dividerItemDecoration)
        holder.recyclerView.setHasFixedSize(false)
        holder.recyclerView.adapter = adapter
        holder.title.text = name

    }

    inner class TodoSectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var recyclerView: RecyclerView

        init {
            this.title = view.findViewById(R.id.title) as TextView
            this.recyclerView = view.findViewById(R.id.recycler_view_todo) as RecyclerView
        }
    }

}