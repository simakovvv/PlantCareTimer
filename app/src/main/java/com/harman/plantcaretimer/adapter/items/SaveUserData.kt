
package com.harman.plantcaretimer.adapter.items

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_save_userdata.view.*

class SaveUserDataItem(id: String) : BaseItem(id) {

    var callback: () -> Unit = {}

    override fun areContentsTheSame(new: BaseItem): Boolean = false
    override fun getChangePayload(new: BaseItem) = bundleOf()
}

class SaveUserDataHolder(view: View) : BaseViewHolder(view) {
    private fun setCallback(callback: () -> Unit) {
        itemView.save_user_data_button.setOnClickListener {
            callback()
        }
    }

    override fun bind(item: BaseItem) {
        setCallback((item as SaveUserDataItem).callback)
    }

    override fun update(bundle: Bundle) {}
}