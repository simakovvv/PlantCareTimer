package com.harman.plantcaretimer.ui.addPlant

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.adapter.MainAdapter
import com.harman.plantcaretimer.utils.Injector
import kotlinx.android.synthetic.main.fragment_add_plant.*

class AddPlantFragment : Fragment(R.layout.fragment_add_plant) {


    private val addPlantViewModel: AddPlantViewModel by viewModels {
        Injector.provideAddPlantViewModelFactory(requireContext())
    }

    private val adapter = MainAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appBarLayout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(
                appBarLayout: AppBarLayout?,
                state: State?
            ) {
                Log.d("STATE", state!!.name)
            }
        })
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        addPlantViewModel.updatePlantsLiveData()
    }

    private fun buildList() {
        adapter.data = addPlantViewModel.buildList(::goToNotifications)
    }

    private fun goToNotifications() {
        findNavController().navigate(R.id.navigation_home)
    }

    private fun initRecyclerView() {
        rv_settings.layoutManager = LinearLayoutManager(context)
        rv_settings.adapter = adapter
        buildList()
    }
}


