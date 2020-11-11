
package com.harman.plantcaretimer.adapter.items

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TimePicker
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.adapter.BaseItem
import com.harman.plantcaretimer.adapter.BaseViewHolder
import com.harman.plantcaretimer.adapter.items.DateTimeItem.Companion.SUBTITLE_KEY
import com.harman.plantcaretimer.adapter.items.DateTimeItem.Companion.TITLE_KEY
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.DATE_FORMAT
import com.harman.plantcaretimer.ui.notifications.NotificationsViewModel.Companion.TIME_FORMAT
import kotlinx.android.synthetic.main.item_calendar.view.*
import java.util.*
enum class DateTime { DATE, TIME}

class DateTimeItem(id: String) : BaseItem(id) {
    companion object {
        const val TITLE_KEY = "TITLE_KEY"
        const val SUBTITLE_KEY = "SUBTITLE_KEY"
    }

    var title: String = ""
    var subTitle: String = ""
    var callback: (date: String) -> Unit = {}
    var type: DateTime = DateTime.DATE

    override fun areContentsTheSame(new: BaseItem): Boolean {
        val newItem = new as DateTimeItem
        return title == newItem.title &&
         subTitle == newItem.subTitle
    }

    override fun getChangePayload(new: BaseItem): Bundle {
        val newItem = new as DateTimeItem
        return Bundle().apply {
            putString(TITLE_KEY, newItem.title)
            putString(SUBTITLE_KEY, newItem.title)
        }
    }
}

class CalendarViewHolder(view: View) : BaseViewHolder(view) {
    private fun setTitle(title: String) {
        itemView.tvTitle.text = title
    }

    private fun setSubTitle(title: String) {
        itemView.etInput.setText(title)
    }

    private fun setInputType() {
        itemView.etInput.isFocusable = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            itemView.etInput.focusable = View.NOT_FOCUSABLE
        }
    }
    private fun setCalendarIcon(dateTime: DateTime?) {
        val type = when(dateTime) {
            DateTime.DATE -> R.drawable.ic_baseline_calendar_today_24
            DateTime.TIME -> R.drawable.ic_baseline_timer_24
            else -> R.color.color_transparent
        }
        itemView.etInput.setCompoundDrawablesWithIntrinsicBounds(
            0,
            0,
            type,
            0
        )
        itemView.etInput.compoundDrawablePadding = 15
    }

    private fun setCalendar(
        editText: EditText,
        callback: ((date: String) -> Unit)?,
        type: DateTime?
    ) {
        if(type == DateTime.DATE ) {
            val calendar = Calendar.getInstance()
            val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = DATE_FORMAT
                editText.setText(sdf.format(calendar.time))
                callback?.invoke(sdf.format(calendar.time))
            }
            editText.setOnClickListener {
                val dialog = DatePickerDialog(
                    editText.context, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                dialog.show()
                dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(editText.resources.getColor(R.color.colorAccent, null))
                dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(editText.resources.getColor(R.color.colorAccent, null))
            }
        } else {
            val calendar = Calendar.getInstance()
            val time = TimePickerDialog.OnTimeSetListener { timePicker: TimePicker, hours: Int, mins: Int ->
                calendar.set(Calendar.HOUR, hours)
                calendar.set(Calendar.MINUTE, mins)
                val sdf = TIME_FORMAT
                editText.setText(sdf.format(calendar.time))
                callback?.invoke(sdf.format(calendar.time))
            }
            editText.setOnClickListener {
                val dialog = TimePickerDialog(
                    editText.context, time, calendar
                        .get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true
                )
                dialog.show()
                dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(editText.resources.getColor(R.color.colorAccent, null))
                dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(editText.resources.getColor(R.color.colorAccent, null))
            }
        }

    }

    override fun bind(item: BaseItem) {
        setTitle((item as? DateTimeItem)?.title ?:"")
        setSubTitle((item as? DateTimeItem)?.subTitle ?:"")
        setInputType()
        setCalendarIcon((item as? DateTimeItem)?.type)
        setCalendar(itemView.etInput,
            (item as? DateTimeItem)?.callback,
            (item as? DateTimeItem)?.type)
    }

    override fun update(bundle: Bundle) {
        setTitle(bundle.getString(TITLE_KEY, ""))
        setSubTitle(bundle.getString(SUBTITLE_KEY, ""))
        setInputType()
    }
}