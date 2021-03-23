package com.xhb.uibase.demo.components.simple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.GridLayoutManager
import com.xhb.uibase.binding.RecyclerViewAdapter
import com.xhb.uibase.demo.R
import com.xhb.uibase.demo.components.basic.XHBButtonFragment
import com.xhb.uibase.demo.core.ComponentFragment
import com.xhb.uibase.demo.core.ViewModel
import com.xhb.uibase.demo.core.ViewStyles
import com.xhb.uibase.demo.core.style.IconStyle
import com.xhb.uibase.demo.core.style.annotation.*
import com.xhb.uibase.demo.databinding.XhbTipViewBinding
import com.xhb.uibase.demo.databinding.XhbTipViewButtonItemBindingImpl
import com.xhb.uibase.demo.databinding.XhbTipViewFragmentBinding
import com.xhb.uibase.widget.XHBButton
import com.xhb.uibase.widget.XHBTipView

class XHBTipViewFragment : ComponentFragment<XhbTipViewFragmentBinding?, XHBTipViewFragment.Model?, XHBTipViewFragment.Styles?>() {

    class Model(fragment: XHBTipViewFragment) : ViewModel() {

        var buttons = arrayOfNulls<Any>(24).toList()

        var tipButton: XHBButton = {
            val button = XHBButton(fragment.context!!)
            button.buttonType = XHBButton.ButtonType.Text
            button.icon = R.drawable.ic_plus
            button.iconAtRight = true
            button
        } ()
    }

    open class Styles : ViewStyles() {
        @Bindable
        @Title("最大宽度")
        @Description("整体最大宽度，设置负数，则自动加上窗口宽度")
        var maxWidth = 200

        @Bindable
        @Title("单行文字")
        @Description("限制单行文字，默认是多行的")
        var singleLine = false

        @Bindable
        @Title("消息内容")
        @Description("自适应消息内容的宽度和高度")
        var message = "你点击了按钮"
    }

    class ToolStyles : Styles() {
        @Bindable
        @Title("建议位置")
        @Description("建议弹出位置，如果左右或者上下超过窗口区域，则会分别自动调整为其他适当位置")
        var location = XHBTipView.Location.TopRight

        @Bindable
        @Title("右侧图标")
        @Description("右侧显示的图标，资源ID类型，一般用于关闭，也可以自定义其他行为")
        @Style(IconStyle::class)
        var rightIcon = 0
    }

    class ToastStyles(fragment: XHBTipViewFragment) : Styles() {

        val layoutManager = GridLayoutManager(fragment!!.context, 4)

        val itemLayout: ItemLayout

        @Bindable
        @Title("建议位置")
        @Description("建议弹出位置，如果左右或者上下超过窗口区域，则会分别自动调整为其他适当位置")
        var location = XHBTipView.Location.TopRight

        //val location: XHBTipView.Location

        @Bindable
        @Title("左侧图标")
        @Description("左侧显示的图标，资源ID类型，一般用于关闭，也可以自定义其他行为")
        @Style(IconStyle::class)
        var leftIcon = 0

        @Bindable
        @Title("提示图标")
        @Description("附加在文字左侧的图标，资源ID类型，不能点击")
        @Style(IconStyle::class)
        var icon = 0

        @Bindable
        @Title("右侧图标")
        @Description("右侧显示的图标，资源ID类型，一般用于关闭，也可以自定义其他行为")
        @Style(IconStyle::class)
        var rightIcon = 0

        private val fragment: XHBTipViewFragment

        init {
            this.fragment = fragment
            itemLayout = ItemLayout(this)
//            if (fragment!!.component.id() == R.id.component_xhb_snack_bars)
//                location = XHBTipView.Location.AutoToast
//            else
//                location = XHBTipView.Location.ManualLayout
        }

        fun buttonClick(view: View) {
            val binding = XhbTipViewBinding.inflate(LayoutInflater.from(fragment!!.context))
            binding.styles = this
            binding.tipView.popAt(view)
            //fragment?.binding?.tipView?.popAt(view)
        }
    }

    class ItemLayout(private val styles: Styles) : RecyclerViewAdapter.UnitTypeItemLayout<Any>(R.layout.xhb_tip_view_button_item) {
        override fun bindView(binding: ViewDataBinding?, item: Any?, position: Int) {
            super.bindView(binding, item, position)
            binding!!.setVariable(BR.styles, styles)
        }
    }

    override fun createStyle(): Styles? {
        return ToastStyles(this)
    }

    companion object {
        private const val TAG = "XHBToolTipFragment"
    }
}