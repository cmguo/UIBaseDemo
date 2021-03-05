package com.xhb.uibase.demo.components.basic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.xhb.uibase.binding.RecyclerViewAdapter
import com.xhb.uibase.demo.R
import com.xhb.uibase.demo.core.ComponentFragment
import com.xhb.uibase.demo.core.SkinManager
import com.xhb.uibase.demo.core.ViewModel
import com.xhb.uibase.demo.core.ViewStyles
import com.xhb.uibase.demo.core.annotation.Description
import com.xhb.uibase.demo.core.annotation.Title
import com.xhb.uibase.demo.databinding.XhbButtonItem2Binding
import com.xhb.uibase.demo.databinding.XhbRadioButtonFragmentBinding
import com.xhb.uibase.demo.databinding.XhbRadioButtonItemBinding
import com.xhb.uibase.demo.view.recycler.PaddingDecoration
import com.xhb.uibase.widget.XHBRadioButton
import skin.support.observe.SkinObservable
import skin.support.observe.SkinObserver

class XHBRadioButtonFragment : ComponentFragment<XhbRadioButtonFragmentBinding?, XHBRadioButtonFragment.Model?, XHBRadioButtonFragment.Style?>(), SkinObserver {
    class Model(fragment: XHBRadioButtonFragment?) : ViewModel() {
        var states = arrayListOf(false, true)
    }

    class Style : ViewStyles() {
        var itemLayout = RadioButtonItemLayout(this)
        var itemDecoration: RecyclerView.ItemDecoration = PaddingDecoration()

        @Bindable
        @Title("禁用")
        @Description("切换到禁用状态")
        var disabled = false

        @Bindable
        @Title("文字")
        @Description("改变文字，按钮会自动适应文字宽度")
        var text = "单选框"

        fun testRadioButtonClick(view: View) {

        }
    }

    class RadioButtonItemLayout(private val style: Style) : RecyclerViewAdapter.UnitTypeItemLayout<Boolean>(R.layout.xhb_radio_button_item) {
        override fun bindView(binding: ViewDataBinding?, item: Boolean, position: Int) {
            super.bindView(binding, item, position)
            binding!!.setVariable(BR.style, style)
            val radio = (binding as XhbRadioButtonItemBinding)!!.radioButton
            radio.isChecked = item
        }
    }

    // this should be in view model, but fragment may simplify things
    val itemClicked = RecyclerViewAdapter.OnItemClickListener<Any> { position, `object` ->
        Log.d(TAG, "itemClicked$`object`")
    }

    fun onClick(view: View?) {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkinManager.addObserver(this)
    }

    override fun onDestroy() {
        SkinManager.removeObserver(this)
        super.onDestroy()
    }

    override fun updateSkin(observable: SkinObservable, o: Any) {
        binding!!.radioButtonList.adapter!!.notifyItemRangeChanged(0, model!!.states.size)
    }

    companion object {
        private const val TAG = "XHBRadioButtonFragment"
    }
}