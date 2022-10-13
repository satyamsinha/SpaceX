package com.example.spacex.launch.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.launch.adapter.RocketLaunchAdapter
import com.example.spacex.launch.adapter.SectionAdapter
import com.example.spacex.launch.data.model.YearData
import com.example.spacex.launch.domain.model.Launch
import com.example.spacex.launch.domain.model.Rocket
import com.example.spacex.launch.ui.launches.LaunchUiState
import com.example.spacex.launch.ui.launches.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedRocketFragmentNew (rocket: Rocket) : Fragment() {
    private lateinit var txtDescription: TextView
    private lateinit var rocketLaunchAdapter: RocketLaunchAdapter
    private lateinit var recylcerView: RecyclerView
    private lateinit var progressCircular: ProgressBar
    private var rocket: Rocket
    private lateinit var root: View
    private val viewModel: MainActivityViewModel by viewModel()

    init {
        this.rocket =rocket
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        root = inflater.inflate(R.layout.detailed_fragment_sectioned, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressCircular=root.findViewById(R.id.progress_circular)
        progressCircular.visibility= View.VISIBLE
        txtDescription =root.findViewById(R.id.txt_description)
        txtDescription.text = rocket.description
        recylcerView = root.findViewById(R.id.recycler_view)
        val mLayoutManager = LinearLayoutManager(context)
        recylcerView.layoutManager = mLayoutManager
        recylcerView.itemAnimator = DefaultItemAnimator()
        recylcerView.addItemDecoration( DividerItemDecoration(context,
            LinearLayoutManager.VERTICAL)
        )

        viewModel.fetchRocketData(rocket.id)
        observeState()
    }


    private fun observeState() {
        viewModel.liveDataLaunchData.observe(viewLifecycleOwner) {
            when (it) {
                is LaunchUiState.Loader -> {
                    progressCircular.visibility = View.VISIBLE
                }
                is LaunchUiState.Success -> {
                    progressCircular.visibility= View.GONE
                    val appCompatActivity = activity as AppCompatActivity
                    progressCircular.visibility = View.GONE
                    sectionedListView(it.docs.launchList, appCompatActivity)
                    /* rocketLaunchAdapter = context?.applicationContext?.let {
                         RocketLaunchAdapter(context!!, (launchUiState as LaunchUiState.Success).docs.launchList, appCompatActivity)
                     }!!
                     recylcerView.adapter = rocketLaunchAdapter
                     recylcerView.invalidate()
                     rocketLaunchAdapter.notifyDataSetChanged()*/

                }
                is LaunchUiState.Failure -> {
                    progressCircular.visibility = View.GONE
                }
            }
        }
    }

    private fun getList(launchList : List<Launch?>) : ArrayList<YearData> {
        val listItem = ArrayList<YearData>()
        val outputList = launchList.distinctBy {
            it?.year
        }
        for(x in outputList ){
            val yearData = YearData (x?.year.toString() , emptyList())
            val launchListTemp = ArrayList<Launch>()
            for(cnt in launchList.indices ){
                if((launchList[cnt]?.year)!! == x?.year) {
                    launchList[cnt]?.let { launchListTemp.add(it) }
                }
            }
            yearData.launchList = launchListTemp
            listItem.add(yearData)
        }
    return  listItem
    }

    private fun sectionedListView(launchList: List<Launch?>, context: Context) {
        val adapter = SectionAdapter(context, getList(launchList))
        recylcerView.setHasFixedSize(true)
        recylcerView.layoutManager = LinearLayoutManager(context)
        recylcerView.adapter = adapter
    }
}