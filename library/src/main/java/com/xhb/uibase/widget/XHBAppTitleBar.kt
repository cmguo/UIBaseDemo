package com.xhb.uibase.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.widget.TextViewCompat
import com.xhb.uibase.R

class XHBAppTitleBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, if (defStyleAttr == 0) R.attr.appTitleBarStyle else defStyleAttr) {

    var title: CharSequence?
        get() = _textView.text
        set(value) {
            _textView.text = value
        }

    var textSize: Float
        get() = _textView.textSize
        set(value) {
            _textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
        }

    var textColor: Int
        get() = _textView.currentTextColor
        set(value) {
            _textView.setTextColor(value)
        }

    @DrawableRes
    var leftButton: Int = 0
        set(value) {
            if (field == value) return
            field = value
            syncButton(_leftButton, value)
            updateLayout()
        }

    @DrawableRes
    var rightButton: Int = 0
        set(value) {
            if (field == value) return
            field = value
            syncButton(_rightButton, value)
        }

    @DrawableRes
    var rightButton2: Int = 0
        set(value) {
            if (field == value) return
            field = value
            syncButton(_rightButton2, value)
        }

    var content: Int = 0
        set(value) {
            if (field == value)
                return

        }

    private var _textView: TextView
    private var _leftButton: XHBButton
    private var _rightButton: XHBButton
    private var _rightButton2: XHBButton
    private var _content: View? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.tip_view, this)
        _leftButton = findViewById(R.id.leftButton)
        _textView = findViewById(R.id.textView)
        _rightButton = findViewById(R.id.rightButton)
        _rightButton2 = findViewById(R.id.rightButton2)

        val a = context.obtainStyledAttributes(attrs, R.styleable.XHBAppTitleBar, R.attr.appTitleBarStyle, 0)
        leftButton = a.getResourceId(R.styleable.XHBAppTitleBar_leftButton, 0)
        rightButton = a.getResourceId(R.styleable.XHBAppTitleBar_rightButton, 0)
        rightButton2 = a.getResourceId(R.styleable.XHBAppTitleBar_rightButton2, 0)
        if (a.hasValue(R.styleable.XHBAppTitleBar_title))
            title = a.getText(R.styleable.XHBAppTitleBar_title)
        val textAppearance = a.getResourceId(R.styleable.XHBAppTitleBar_android_textAppearance, 0)
        if (textAppearance > 0)
            TextViewCompat.setTextAppearance(_textView, textAppearance)
        a.recycle()
    }

    companion object {
        private const val TAG = "XHBAppTitleBar"
    }

    private fun syncButton(button: XHBButton, content: Int) {
        if (content == 0) {
            button.visibility = GONE
        } else {
            button.content = content
            button.visibility = VISIBLE
        }
    }

    private fun updateLayout() {
        if (leftButton == 0) {

        }
    }

    /* private */

}


