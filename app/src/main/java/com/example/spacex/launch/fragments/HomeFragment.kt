package com.example.spacex.launch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.launch.adapter.RocketListAdpater
import com.example.spacex.launch.ui.launches.HomeUiState
import com.example.spacex.launch.ui.launches.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var rocketListAdapter: RocketListAdpater
    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var progressCircular : ProgressBar
    private  lateinit  var root : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        root = inflater.inflate(R.layout.main_fragment, container, false)
        return root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = root.findViewById(R.id.recycler_view)
        progressCircular=root.findViewById(R.id.progress_circular)
        progressCircular.visibility= View.VISIBLE
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchRocketListData()
        observeState()
    }
    private fun observeState() {
        viewModel.liveDataHomeData.observe(this) { homeUiState ->
            when (homeUiState) {
                is HomeUiState.Loader -> {
                    root.findViewById<ProgressBar>(R.id.progress_circular).visibility = View.VISIBLE
                }
                is HomeUiState.Success -> {
                    val appCompatActivity = activity as AppCompatActivity
                    progressCircular.visibility = View.GONE
                    rocketListAdapter = context?.applicationContext?.let {
                        RocketListAdpater(context!!, homeUiState.rocketList, appCompatActivity)
                    }!!
                    recyclerView.adapter = rocketListAdapter
                    recyclerView.invalidate()
                    rocketListAdapter.notifyDataSetChanged()

                }
                is HomeUiState.Failure -> {
                    progressCircular.visibility = View.GONE
                }
            }
        }
    }

}