
package com.harman.plantcaretimer.adapter.items

import android.os.Bundle
import android.view.View
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_title.view.*


class TitleItem (id: String) : BaseItem(id) {
    companion object {
        const val TEXT_KEY = "text_key"
    }

    var text: String = ""
    var textRight: String? = null
    var callback: () -> Unit = {}

    override fun areContentsTheSame(new: BaseItem): Boolean {
        val newItem = new as TitleItem
        return text == newItem.text
    }

    override fun getChangePayload(new: BaseItem): Bundle {
        val newItem = new as TitleItem
        return Bundle().apply {
            putString(TEXT_KEY, newItem.text)
        }
    }
}

class TitleViewHolder(private val view: View) : BaseViewHolder(view) {
    private fun setText(text: String) {
        itemView.tvTitle.text = text
    }

    override fun bind(item: BaseItem) {
        (item as? TitleItem)?.let { titleItem ->
            setText(titleItem.text)
            itemView.tvTitle.setOnClickListener {
                titleItem.callback()
            }
        }
    }

    override fun update(bundle: Bundle) {
        setText(bundle.getString(TitleItem.TEXT_KEY, ""))
    }
}