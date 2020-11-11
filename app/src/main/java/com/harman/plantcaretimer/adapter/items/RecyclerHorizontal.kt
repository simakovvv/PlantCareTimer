package com.harman.plantcaretimer.adapter.items

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.adapter.*
import com.harman.plantcaretimer.adapter.items.VerticalRecyclerItem.Companion.TEXT_KEY
import kotlinx.android.synthetic.main.item_horizontal_recycler.view.*


class HorizontalRecyclerItem(id: String) : BaseItem(id) {
    companion object {
        const val TEXT_KEY = "text_key"
    }
    var text: String = ""
    var callback: () -> Unit = {}
    var itemList: List<BaseItem> = listOf()

    override fun areContentsTheSame(new: BaseItem): Boolean {
        val newItem = new as HorizontalRecyclerItem
        return text == newItem.text &&
                itemList.size == newItem.itemList.size
    }

    override fun getChangePayload(new: BaseItem): Bundle {
        val newItem = new as HorizontalRecyclerItem
        return Bundle().apply {
            putString(TEXT_KEY, newItem.text)
        }
    }
}

class HorizontalRecyclerViewHolder(private val view: View) : BaseViewHolder(view) {
    private val adapter = MainAdapter()

    private fun setText(text: String) {
        itemView.text_plants_group.text = text
    }

    private fun setAdapter() {
        itemView.rv_plants.adapter = adapter
    }

    private fun setLayoutManager() {
        itemView.rv_plants.layoutManager =
            LinearLayoutManager(itemView.context,  RecyclerView.HORIZONTAL, false )
    }

    private fun setCallBack(callback: () -> Unit) {
        itemView.text_plants_group.setOnClickListener {
            callback()
        }
    }

    private fun setItemsList(itemList: List<BaseItem>) {
        adapter.data = itemList
    }

    override fun bind(item: BaseItem) {
        (item as? HorizontalRecyclerItem)?.let { rvItem ->
            setText(rvItem.text)
            setAdapter()
            setLayoutManager()
            setItemsList(rvItem.itemList)
            setCallBack(rvItem.callback)
        }
    }

    override fun update(bundle: Bundle) {
        setText(bundle.getString(TEXT_KEY, ""))
    }
}