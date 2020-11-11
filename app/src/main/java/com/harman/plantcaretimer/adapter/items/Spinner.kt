
package com.harman.plantcaretimer.adapter.items

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.BaseViewHolder
import com.harman.plantcaretimer.adapter.items.SpinnerItem.Companion.IS_EDITABLE
import com.harman.plantcaretimer.adapter.items.SpinnerItem.Companion.TITLE_KEY
import kotlinx.android.synthetic.main.item_edit_text.view.tvTitle
import kotlinx.android.synthetic.main.item_spinner.view.*


class SpinnerItem(id: String) : BaseItem(id) {
    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        const val IS_EDITABLE = "IS_EDITABLE"
        const val ITEM_ID = "ITEM_ID"
    }

    var itemID: String = ""
    var title: String = ""
    var itemsList: List<String> = listOf()
    var isEditable: Boolean = false

    override fun areContentsTheSame(new: BaseItem): Boolean {
        val newItem = new as SpinnerItem
        return title == newItem.title
                && isEditable == newItem.isEditable
                && itemID == newItem.itemID
                && itemsList.size == newItem.itemsList.size
    }

    override fun getChangePayload(new: BaseItem): Bundle {
        val newItem = new as SpinnerItem
        return Bundle().apply {
            putString(TITLE_KEY, newItem.title)
            putBoolean(IS_EDITABLE, newItem.isEditable)
            putString(ITEM_ID, newItem.id)
        }
    }
}

class SpinnerViewHolder(view: View) : BaseViewHolder(view) {
    private fun setTitle(text: String) {
        itemView.tvTitle.text = text
    }

    private fun setSubtitle(data: List<String>?) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(itemView.context, R.layout.simple_spinner_item, data?.toTypedArray()!!)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        itemView.spinner.adapter = adapter;
        itemView.spinner.prompt = "SpinnerTitle";
        itemView.spinner.setSelection(0);
    }

    private fun setEditable(value: Boolean) {

    }


    override fun bind(item: BaseItem) {
        setTitle((item as? SpinnerItem)?.title?:"")
        setSubtitle((item as? SpinnerItem)?.itemsList)
        setEditable((item as? SpinnerItem)?.isEditable ?: false)
    }

    override fun update(bundle: Bundle) {
        setTitle(bundle.getString(TITLE_KEY, ""))
        setEditable(bundle.getBoolean(IS_EDITABLE, false))
    }
}