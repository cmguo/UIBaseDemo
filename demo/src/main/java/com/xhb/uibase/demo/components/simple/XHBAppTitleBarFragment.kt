package com.xhb.uibase.demo.components.simple

import androidx.databinding.Bindable
import com.xhb.uibase.demo.core.ComponentFragment
import com.xhb.uibase.demo.core.ViewModel
import com.xhb.uibase.demo.core.ViewStyles
import com.xhb.uibase.demo.core.style.ContentStyle
import com.xhb.uibase.demo.core.style.LayoutStyle
import com.xhb.uibase.demo.core.style.annotation.Description
import com.xhb.uibase.demo.core.style.annotation.Style
import com.xhb.uibase.demo.core.style.annotation.TextAppearanceStyle
import com.xhb.uibase.demo.core.style.annotation.Title
import com.xhb.uibase.demo.databinding.XhbAppTitleBarFragmentBinding

class XHBAppTitleBarFragment : ComponentFragment<XhbAppTitleBarFragmentBinding?, XHBAppTitleBarFragment.Model?, XHBAppTitleBarFragment.Styles?>() {

    class Model(fragment: XHBAppTitleBarFragment?) : ViewModel() {
    }

    class Styles(fragment: XHBAppTitleBarFragment?) : ViewStyles() {

        @Bindable
        @Title("左侧内容")
        @Description("左侧按钮的内容，参见按的 content 样式")
        @Style(ContentStyle::class)
        var leftButton = 0

        @Bindable
        @Title("右侧内容")
        @Description("右侧按钮的内容，参见按的 content 样式")
        @Style(ContentStyle::class)
        var rightButton = 0

        @Bindable
        @Title("右侧内容2")
        @Description("右侧第2个按钮的内容，参见按的 content 样式")
        @Style(ContentStyle::class)
        var rightButton2 = 0

        @Bindable
        @Title("中间内容")
        @Description("中间内容，布局（style）资源ID")
        @Style(LayoutStyle::class)
        var content = 0

        @Bindable
        @Title("标题")
        @Description("标题文字，一般在中间显示；如果没有左侧按钮内容，则在左侧大标题样式展示")
        var title = "标题"

        @Bindable
        @Title("标题样式")
        @Description("标题样式（android:textAppearance），应用于标题；默认样式会根据位置自动计算设置，所以一般不需要设置")
        @Style(TextAppearanceStyle::class)
        var textAppearance = 0
    }

    companion object {
        private const val TAG = "XHBAmountViewFragment"
    }
}