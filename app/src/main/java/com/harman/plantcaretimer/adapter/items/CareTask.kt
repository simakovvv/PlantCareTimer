package com.harman.plantcaretimer.adapter.items

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.BaseViewHolder
import com.harman.plantcaretimer.adapter.items.CareTaskItem.Companion.DATE_KEY
import com.harman.plantcaretimer.adapter.items.CareTaskItem.Companion.ICON_KEY
import com.harman.plantcaretimer.adapter.items.CareTaskItem.Companion.SUBTEXT_KEY
import com.harman.plantcaretimer.adapter.items.CareTaskItem.Companion.TEXT_KEY
import kotlinx.android.synthetic.main.item_task.view.*

class CareTaskItem(id: String) : BaseItem(id) {
    companion object {
        const val TEXT_KEY = "text_key"
        const val SUBTEXT_KEY = "subtext_key"
        const val DATE_KEY = "date_key"
        const val ICON_KEY = "icon_key"
    }

    var title: String = ""
    var subtitle: String = ""
    var date: String = ""
    var icon: Uri? = Uri.EMPTY
    var callback: (item: BaseItem) -> Unit = {}

    override fun areContentsTheSame(new: BaseItem): Boolean {
        val newItem = new as CareTaskItem
        return title == newItem.title &&
               subtitle == newItem.subtitle &&
                icon == newItem.icon &&
                date == newItem.date

    }

    override fun getChangePayload(new: BaseItem): Bundle {
        val newItem = new as CareTaskItem
        return Bundle().apply {
            putString(TEXT_KEY, newItem.title)
            putString(SUBTEXT_KEY, newItem.subtitle)
            putString(DATE_KEY, newItem.date)
            putString(ICON_KEY, newItem.icon.toString())
        }
    }
}

class CareTaskViewHolder(private val view: View) : BaseViewHolder(view) {
    private fun setText(text: String) {
        itemView.tvTitle.text = text
    }

    private fun setSubTitle(subtitle: String) {
        itemView.tvSubTitle.text = subtitle
    }

    private fun setDateText(text: String) {
        itemView.tvDateTitle.text = text
    }

    private fun setIcon(icon:Uri) {
        if(icon != Uri.EMPTY)
        itemView.ivImage.setImageURI(icon)
    }

    override fun bind(item: BaseItem) {
        (item as? CareTaskItem)?.let { titleItem ->
            setText(titleItem.title)
            setSubTitle(titleItem.subtitle)
            setDateText(titleItem.date)
            setIcon(titleItem.icon!!)
            itemView.setOnClickListener {
                titleItem.callback(item)
            }
        }
    }

    override fun update(bundle: Bundle) {
        setText(bundle.getString(TEXT_KEY, ""))
        setSubTitle(bundle.getString(SUBTEXT_KEY,""))
        setDateText(bundle.getString(DATE_KEY, ""))
        setIcon(Uri.parse(bundle.getString(ICON_KEY, Uri.EMPTY.toString())))
    }
}