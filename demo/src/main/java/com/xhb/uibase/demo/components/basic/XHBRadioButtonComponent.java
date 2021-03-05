package com.xhb.uibase.demo.components.basic;

import com.google.auto.service.AutoService;
import com.xhb.uibase.demo.R;
import com.xhb.uibase.demo.core.Component;
import com.xhb.uibase.demo.core.ComponentFragment;
import com.xhb.uibase.demo.core.annotation.Author;

@AutoService(Component.class)
@Author("Fish")
public class XHBRadioButtonComponent implements Component {
    @Override
    public int id() {
        return R.id.xhb_component_radio_buttons;
    }

    @Override
    public int group() {
        return R.string.group_basic;
    }

    @Override
    public int icon() {
        return android.R.drawable.btn_plus;
    }

    @Override
    public int title() {
        return R.string.component_xhb_radiobuttons;
    }

    @Override
    public int description() {
        return R.string.component_xhb_radiobuttons_desc;
    }

    @Override
    public Class<? extends ComponentFragment> fragmentClass() {
        return XHBRadioButtonFragment.class;
    }

}
