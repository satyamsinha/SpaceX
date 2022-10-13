package com.example.spacex.launch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.launch.domain.model.Launch

class RocketLaunchAdapter(context: Context, rocketLaunchList: List<Launch?>, appCompatActivity: AppCompatActivity)  :  RecyclerView.Adapter<RocketLaunchAdapter.RocketLaunchViewHolder>() {
    private var activity: AppCompatActivity
    private var listRocketLaunch:List<Launch?>

    init {
        this.listRocketLaunch= rocketLaunchList
        this.activity=appCompatActivity
    }
    class RocketLaunchViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_rocket_launch, parent, false)) {
        var txtCountry: TextView? = null
        var txtName: TextView? = null
        private var imgStatus: ImageView?=null
        var txtEngine: TextView?=null
        private var layoutList: ConstraintLayout?=null
        init {
            txtName= itemView.findViewById(R.id.txt_name)
            imgStatus= itemView.findViewById(R.id.img_status)
            txtCountry= itemView.findViewById(R.id.txt_country)
            txtEngine= itemView.findViewById(R.id.txt_engine)
            layoutList=itemView.findViewById(R.id.layout_list)

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RocketLaunchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RocketLaunchViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(
        holder:RocketLaunchViewHolder,
        position: Int
    ) {
        holder.txtName?.text = "Name : " + (listRocketLaunch[position]?.name ?: "")
        holder.txtEngine?.text = "Date of Launch : " + listRocketLaunch[position]?.date_local
        holder.txtCountry?.text = "Status : " + listRocketLaunch[position]?.success

    }
    override fun getItemCount(): Int {
        return listRocketLaunch.size
    }

}
