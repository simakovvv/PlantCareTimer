package com.harman.plantcaretimer.adapter

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.adapter.items.*

abstract class BaseItem(val id: String) {
    abstract fun areContentsTheSame(new: BaseItem): Boolean
    fun areItemsTheSame(new: BaseItem): Boolean = id == new.id
    abstract fun getChangePayload(new: BaseItem): Bundle
}

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: BaseItem)

    abstract fun update(bundle: Bundle)
}