package com.example.spacex.launch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.R
import com.example.spacex.launch.domain.model.Launch

class SubSectionAdapter(context: Context, items: List<Launch>) :
    RecyclerView.Adapter<SubSectionAdapter.TodoViewHolder>() {

    private val items: ArrayList<Launch>?
    private val context: Context

    init {
        this.items = items as ArrayList<Launch>?
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, null)
        view.layoutParams =
            RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (items != null) {
            return items.count()
        }

        return 0
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val title = items?.get(position)?.name
        val success  = if(items?.get(position)?.success ==true) "Success" else  "Failure"
        holder.todoTitle.text = title
        holder.todoStatus.text = "Status : $success"


        Glide.with(context)
            .load(items?.get(position)?.links?.patch?.large)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.todoImage)

    }

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var todoTitle: TextView
        var todoStatus: TextView
        var todoImage: ImageView

        init {
            this.todoTitle = view.findViewById(R.id.todo_title) as TextView
            this.todoStatus = view.findViewById(R.id.tv_status) as TextView
            this.todoImage = view.findViewById(R.id.todo_image) as ImageView
        }
    }

}