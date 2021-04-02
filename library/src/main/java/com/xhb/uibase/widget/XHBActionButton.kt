package com.xhb.uibase.widget

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.VectorDrawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.xhb.uibase.R
import com.xhb.uibase.resources.RoundWrapDrawable

class XHBActionButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : XHBButton(context, attrs)
{
    override var icon = 0
        set(value) {
            super.icon = value
            field = value
            val icon = iconDrawable
            if (icon is VectorDrawable) {
                icon.setTintList(ContextCompat.getColorStateList(context, R.color.bluegrey800_disabled))
                icon.setBounds(Rect(0, 0, icon.intrinsicWidth, icon.intrinsicHeight))
                val drawable = RoundWrapDrawable(icon)
                drawable.fillColor = ContextCompat.getColorStateList(context, R.color.bluegrey_100)
                iconDrawable = drawable
            }
        }
}
