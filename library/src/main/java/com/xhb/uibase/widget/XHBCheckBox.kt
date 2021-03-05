package com.xhb.uibase.widget

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.xhb.uibase.R
import com.xhb.uibase.resources.ShapeDrawables

class XHBCheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatCheckBox(context, attrs, defStyleAttr) {

    enum class CheckedState {
        NotChecked,
        HalfChecked,
        FullChecked
    }

    companion object {
        val backgroundDrawable = ShapeDrawables.Config(GradientDrawable.RECTANGLE,
            R.dimen.checkbox_radius, R.color.xhb_bluegrey00_checked_disabled,
            R.dimen.checkbox_border_size, R.color.xhb_bluegrey500_checked_disabled,
            R.dimen.checkbox_icon_size, R.dimen.checkbox_icon_size
        )
    }

    private var half_: Boolean = false

    init {
        buttonDrawable = ShapeDrawables.getDrawable(context, backgroundDrawable)
    }

    var checkedState: CheckedState
        get() {
            return if (isChecked()) (if (half_) CheckedState.HalfChecked else CheckedState.FullChecked)
            else CheckedState.NotChecked
        }
        set(value) {
            half_ = value == CheckedState.HalfChecked
            isChecked = value != CheckedState.NotChecked
            refreshDrawableState()
        }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (half_)
            mergeDrawableStates(drawableState, intArrayOf(R.attr.half_checked))
        return drawableState
    }
}