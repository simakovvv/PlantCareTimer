package com.harman.plantcaretimer.utils

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.Window
import com.harman.plantcaretimer.R
import com.harman.plantcaretimer.data.careTask.CareTask
import kotlinx.android.synthetic.main.alert_care_task.*
import kotlin.math.ceil


class CareTaskAlert(
    private val careTask: CareTask,
    context: Context,
    private val isSusses:(Boolean) -> Unit
) : Dialog(context, R.style.TransparentDialog) {

    companion object {
        private const val DEF_PPH = 800
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_care_task)

        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        iv_plant_image.setImageURI(careTask.plant.icon)
        tv_plant_name.text = careTask.plant.name
        tv_task_label.text = careTask.name
        tv_task_description.text = careTask.description
        btn_ok.setOnClickListener {
            if(!etInput.text.isNullOrEmpty()) {
                tv_output.text = calculateData(etInput.text)
                etInput.visibility = View.INVISIBLE
                tv_task_description.visibility = View.INVISIBLE
                btn_ok.text = "OK"
                context.hideKeyboardFrom(this.currentFocus)
                btn_ok.setOnClickListener {
                    dismiss()
                }
                tv_output.visibility = View.VISIBLE
                isSusses(true)
            }
        }
        setCancelable(true)
    }

    private fun calculateData(value: Editable?): String {
        val missedPph = DEF_PPH - Integer.valueOf(value.toString())
        val coefficient = (missedPph *30)/11.6
        val grow = ceil((coefficient * 15)/1000)
        val micro = ceil((coefficient * 10)/1000)
        val bloom = ceil((coefficient *5)/1000)
        return StringBuilder()
            .append("Для стабилизации ppH до $DEF_PPH поднимите его на $missedPph \n")
            .append("Grow: $grow mL\n")
            .append("Micro: $micro mL\n")
            .append("Bloom: $bloom mL\n")
            .toString()
    }
}