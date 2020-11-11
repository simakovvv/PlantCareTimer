package com.harman.plantcaretimer.adapter.items

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.BaseViewHolder
import com.harman.plantcaretimer.adapter.items.EditTextItem.Companion.IS_EDITABLE
import com.harman.plantcaretimer.adapter.items.EditTextItem.Companion.SUBTITLE_KEY
import com.harman.plantcaretimer.adapter.items.EditTextItem.Companion.TITLE_KEY
import com.harman.plantcaretimer.adapter.items.EditTextItem.Companion.VALUE_TITLE
import com.harman.plantcaretimer.utils.hideKeyboardFrom
import kotlinx.android.synthetic.main.item_edit_text.view.*


class EditTextItem(id: String) : BaseItem(id) {
    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        const val SUBTITLE_KEY = "SUBTITLE_KEY"
        const val ITEM_ID = "ITEM_ID"
        const val IS_EDITABLE = "IS_EDITABLE"
        const val VALUE_TITLE = "VALUE_TITLE"
        const val HINT = "hint"
    }

    var itemID: String = ""
    var title: String = ""
    var subtitle: String = ""
    var hintTitle: String = "-1"
    var isEditable: Boolean = true
    var hint: String = "0"
    var callback: (String) -> Unit = {}

    override fun areContentsTheSame(new: BaseItem): Boolean {
        val newItem = new as EditTextItem
        return title == newItem.title
                && subtitle == newItem.subtitle
                && itemID == newItem.itemID
                && isEditable == newItem.isEditable
                && hintTitle == newItem.hintTitle
                && hint == newItem.hint
    }

    override fun getChangePayload(new: BaseItem): Bundle {
        val newItem = new as EditTextItem
        return Bundle().apply {
            putString(TITLE_KEY, newItem.title)
            putString(SUBTITLE_KEY, newItem.subtitle)
            putString(ITEM_ID, newItem.id)
            putBoolean(IS_EDITABLE, newItem.isEditable)
            putString(VALUE_TITLE, newItem.hintTitle)
            putString(HINT, newItem.hint)
        }
    }
}

class EditTextHolder(view: View) : BaseViewHolder(view) {
    private fun setTitle(text: String) {
        itemView.tvTitle.text = text
    }

    private fun setSubtitle(text: String) {
        itemView.tvSecondTitle.setText(text, TextView.BufferType.EDITABLE)
    }

    private fun setEditable(value: Boolean) {
        itemView.etInput.isEnabled = value
        if (value) {
            itemView.etInput.background = EditText(itemView.context).background
        } else {
            itemView.etInput.setBackgroundResource(android.R.color.transparent)
        }
    }

    private fun setHintTitle(value: String) {
        if(value != "-1"){
            itemView.etInput.hint = value.toString()
        }
    }

    private fun setHint(hint: String?) {
        itemView.etInput.hint = hint
    }

    private fun setCallback(callback: ((String) -> Unit)?) {
        itemView.etInput.setOnEditorActionListener(
            OnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || (event.action === KeyEvent.ACTION_DOWN
                            && event.keyCode === KeyEvent.KEYCODE_ENTER)
                ) {
                    itemView.context.hideKeyboardFrom(v)
                    callback?.invoke(v.text.toString())
                    return@OnEditorActionListener true
                }
                false
            })
    }

    override fun bind(item: BaseItem) {
        setEditable((item as? EditTextItem)?.isEditable ?: false)
        setTitle((item as? EditTextItem)?.title?:"")
        setSubtitle((item as? EditTextItem)?.subtitle.orEmpty())
        setHintTitle((item as? EditTextItem)?.hintTitle?:"-1")
        setHint((item as? EditTextItem)?.hint)
        setCallback((item as? EditTextItem)?.callback)
    }

    override fun update(bundle: Bundle) {
        setTitle(bundle.getString(TITLE_KEY, ""))
        setSubtitle(bundle.getString(SUBTITLE_KEY, ""))
        setEditable(bundle.getBoolean(IS_EDITABLE, false))
        setHintTitle(bundle.getString(VALUE_TITLE, "-1"))
    }
}