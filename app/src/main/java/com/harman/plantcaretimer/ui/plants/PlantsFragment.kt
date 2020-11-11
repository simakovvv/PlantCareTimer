package com.harman.plantcaretimer.ui.plants

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.adapter.MainAdapter
import com.harman.plantcaretimer.data.Plant
import com.harman.plantcaretimer.utils.Injector
import kotlinx.android.synthetic.main.fragment_plants.*


class PlantsFragment : Fragment(R.layout.fragment_plants) {

    private val plantsViewModel: PlantsViewModel by viewModels {
        Injector.providePlantsViewModelFactory(requireContext())
    }

    private val observer = Observer<List<Plant>>(::buildList)
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantsViewModel.plantsList.observe(viewLifecycleOwner, observer)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        plantsViewModel.updatePlantsLiveData()
    }

    private fun buildList(plantsUpdated: List<Plant>? = null) {
        val plants = plantsViewModel.plantsList.value.orEmpty()
        adapter.data = plantsViewModel.buildList(plants, ::navigateTo)
    }

    private fun navigateTo() = findNavController().navigate(R.id.navigation_notifications)

    private fun initRecyclerView() {
        rv_root.layoutManager = LinearLayoutManager(context)
        rv_root.adapter = adapter
        buildList()
    }
}

