package com.example.spacex.launch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spacex.R
import com.example.spacex.launch.domain.model.Rocket
import com.example.spacex.launch.fragments.DetailedRocketFragmentNew
import com.example.spacex.launch.ui.launches.addFragment

class RocketListAdpater(context: Context, rocketList: List<Rocket> , appCompatActivity: AppCompatActivity)  :  RecyclerView.Adapter<RocketListAdpater.RocketViewHolder>() {
    private var activity: AppCompatActivity
    private var listRocket:List<Rocket>

    init {
        this.listRocket=rocketList
        this.activity=appCompatActivity
    }
    class RocketViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_rocket, parent, false)) {
        var txtCountry: TextView? = null
        var txtName: TextView? = null
        var imgStatus: ImageView?=null
        var txtEngine: TextView?=null
        var layoutList: ConstraintLayout?=null
        init {
            txtName= itemView.findViewById(R.id.txt_name)
            imgStatus= itemView.findViewById(R.id.img_status)
            txtCountry= itemView.findViewById(R.id.txt_country)
            txtEngine= itemView.findViewById(R.id.txt_engine)
            layoutList=itemView.findViewById(R.id.layout_list)

        }

    }
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RocketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        this.context= parent.context
        return RocketViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(
        holder:RocketViewHolder,
        position: Int
    ) {
        holder.txtName?.text = "Name : " + listRocket[position].name
        holder.txtEngine?.text = "No of Engine : " + listRocket[position].engines.number
        holder.txtCountry?.text = "Country : " + listRocket[position].country
        holder.imgStatus?.let {
            Glide.with(context)
                .load(listRocket?.get(position)?.flickr_images?.get(0))
                .error(android.R.drawable.ic_menu_report_image)
                .into(it)
        }
        holder.layoutList?.setOnClickListener {
           // activity.addFragment(RocketDetailFragment(listRocket[position]))
            activity.addFragment(DetailedRocketFragmentNew(listRocket[position]))
        }
    }
    override fun getItemCount(): Int {
        return listRocket.size
    }

}
