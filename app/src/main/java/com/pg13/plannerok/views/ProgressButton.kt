package com.pg13.plannerok.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.LayoutProgressButonBinding


class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutProgressButonBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.layout_progress_buton,
        this,
        true
    )

    fun showProgress(show: Boolean) {
        if (show) {
            binding.apply {
                progressBar.visibility = View.VISIBLE
                button.visibility = View.INVISIBLE
            }
        } else {
            binding.apply {
                progressBar.visibility = View.INVISIBLE
                button.visibility = View.VISIBLE
            }
        }
    }

    fun setButtonEnabled(enabled: Boolean) {
        binding.buttonEnabled = enabled
    }

    fun setTextRes(textRes: String?) {
        binding.textRes = textRes
    }

    fun setOnClick(onClick: OnClickListener?) {
        binding.onClick = onClick
    }

    fun setButtonColor(buttonColor: ColorStateList) {
        binding.buttonColor = buttonColor
    }
}