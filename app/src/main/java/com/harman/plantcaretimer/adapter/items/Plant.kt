package com.harman.plantcaretimer.adapter.items

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantItem(id: String) : BaseItem(id) {
    companion object {
        const val TEXT_KEY = "text_key"
        const val ICON_KEY = "icon_key"
    }

    var text: String = ""
    var icon: Uri? = Uri.EMPTY
    var callback: () -> Unit = {}

    override fun areContentsTheSame(new: BaseItem): Boolean {
        val newItem = new as PlantItem
        return text == newItem.text &&
                icon == newItem.icon

    }

    override fun getChangePayload(new: BaseItem): Bundle {
        val newItem = new as PlantItem
        return Bundle().apply {
            putString(TEXT_KEY, newItem.text)
            putString(ICON_KEY, newItem.icon.toString())
        }
    }
}

class PlantViewHolder(private val view: View) : BaseViewHolder(view) {
    private fun setText(text: String) {
        itemView.tvText.text = text
    }

    private fun setIcon(icon:Uri) {
        if(icon != Uri.EMPTY)
        itemView.image.setImageURI(icon)
    }

    override fun bind(item: BaseItem) {
        (item as? PlantItem)?.let { titleItem ->
            setText(titleItem.text)
            setIcon(titleItem.icon!!)
            itemView.setOnClickListener {
                titleItem.callback()
            }
        }
    }

    override fun update(bundle: Bundle) {
        setText(bundle.getString(PlantItem.TEXT_KEY, ""))
        setText(bundle.getString(PlantItem.TEXT_KEY, ""))
        setIcon(Uri.parse(bundle.getString(PlantItem.ICON_KEY, Uri.EMPTY.toString())))
    }
}