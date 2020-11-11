
package com.harman.plantcaretimer.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.adapter.items.*


open class MainAdapter : RecyclerView.Adapter<BaseViewHolder>(){

    var data: List<BaseItem> = listOf()
        set(value) {
            val result = DiffUtil.calculateDiff(BaseDiffUtilCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view  = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return when(viewType){
            R.layout.item_calendar -> CalendarViewHolder(view)
            R.layout.item_task -> CareTaskViewHolder(view)
            R.layout.item_horizontal_recycler -> HorizontalRecyclerViewHolder(view)
            R.layout.item_vertical_recycler -> VerticalRecyclerViewHolder(view)
            R.layout.item_spinner -> SpinnerViewHolder(view)
            R.layout.item_title -> TitleViewHolder(view)
            R.layout.item_plant -> PlantViewHolder(view)
            R.layout.item_edit_text -> EditTextHolder(view)
            R.layout.item_save_userdata -> SaveUserDataHolder(view)
            else -> throw IllegalArgumentException("Unknown viewType")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is DateTimeItem -> R.layout.item_calendar
            is CareTaskItem -> R.layout.item_task
            is PlantItem -> R.layout.item_plant
            is TitleItem -> R.layout.item_title
            is VerticalRecyclerItem -> R.layout.item_vertical_recycler
            is HorizontalRecyclerItem -> R.layout.item_horizontal_recycler
            is SpinnerItem -> R.layout.item_spinner
            is EditTextItem -> R.layout.item_edit_text
            is SaveUserDataItem -> R.layout.item_save_userdata
            else -> throw IllegalArgumentException("Unknown item type")
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val bundlePayloads = payloads.filterIsInstance<Bundle>()
        if(bundlePayloads.isEmpty()) {
            holder.bind(data[position])
        }
        else holder.update(bundlePayloads[0] as Bundle)
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        this.onBindViewHolder(holder, position, mutableListOf())
    }

    class BaseDiffUtilCallback(
        private val oldList : List<BaseItem>,
        private val newList : List<BaseItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].areItemsTheSame(newList[newItemPosition])
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].areContentsTheSame(newList[newItemPosition])
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any =
            oldList[oldItemPosition].getChangePayload(newList[newItemPosition])
    }
}