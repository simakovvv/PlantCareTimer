package com.harman.plantcaretimer.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.adapter.MainAdapter
import com.harman.plantcaretimer.data.careTask.CareTask
import com.harman.plantcaretimer.utils.Injector
import kotlinx.android.synthetic.main.fragment_plants.*

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val notificationsViewModel: NotificationsViewModel by viewModels {
        Injector.provideNotificationsViewModelFactory(requireContext())
    }

    private val observer = Observer<List<CareTask>>(::buildList)
    private val adapter = MainAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notificationsViewModel.careTaskList.observe(viewLifecycleOwner, observer)
        initRecyclerView()
    }

    private fun buildList(updated: List<CareTask>? = null) {
        adapter.data = notificationsViewModel.buildList(updated, requireContext() )
    }

    private fun initRecyclerView() {
        rv_root.layoutManager = LinearLayoutManager(context)
        rv_root.adapter = adapter
        buildList()
    }
}
