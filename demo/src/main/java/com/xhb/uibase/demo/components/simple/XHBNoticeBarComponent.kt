package com.xhb.uibase.demo.components.simple

import com.google.auto.service.AutoService
import com.xhb.uibase.demo.R
import com.xhb.uibase.demo.core.Component
import com.xhb.uibase.demo.core.ComponentFragment

@AutoService(Component::class)
class XHBNoticeBarComponent : Component {
    override fun id(): Int {
        return R.id.component_xhb_notice_bars
    }

    override fun group(): Int {
        return R.string.group_simple
    }

    override fun icon(): Int {
        return android.R.drawable.btn_plus
    }

    override fun title(): Int {
        return R.string.component_xhb_notice_bars
    }

    override fun description(): Int {
        return R.string.component_xhb_notice_bars_desc
    }

    override fun fragmentClass(): Class<out ComponentFragment<*, *, *>?> {
        return XHBTipViewFragment::class.java
    }
}