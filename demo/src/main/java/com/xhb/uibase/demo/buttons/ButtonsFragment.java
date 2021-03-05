package com.xhb.uibase.demo.buttons;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xhb.uibase.binding.RecyclerViewAdapter;
import com.xhb.uibase.demo.R;
import com.xhb.uibase.demo.core.ComponentFragment;
import com.xhb.uibase.demo.core.SkinManager;
import com.xhb.uibase.demo.core.Styles;
import com.xhb.uibase.demo.core.ViewModel;
import com.xhb.uibase.demo.core.ViewStyles;
import com.xhb.uibase.demo.core.annotation.Title;
import com.xhb.uibase.demo.core.annotation.Values;
import com.xhb.uibase.demo.databinding.ButtonsFragmentBinding;
import com.xhb.uibase.demo.view.recycler.PaddingDecoration;

import java.util.Map;

import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;

public class ButtonsFragment extends ComponentFragment<ButtonsFragmentBinding, ButtonsFragment.Model, ButtonsFragment.Style>
        implements SkinObserver {

    private static final String TAG = "ButtonsComponent";

    public static class Model extends ViewModel {
        private Map<String, Integer> styles;

        public Model(ButtonsFragment fragment) {
            if (fragment.getComponent().id() == R.id.component_buttons)
                styles = Styles.buttonStyles(fragment.getContext());
            else
                styles = Styles.buttonStyles2(fragment.getContext());
        }

        public Map<String, Integer> getStyles() {
            return styles;
        }
    }

    public static class Style extends ViewStyles {
        public ButtonItemLayout itemLayout = new ButtonItemLayout(this);
        public RecyclerView.ItemDecoration itemDecoration = new PaddingDecoration();
        @Bindable @Title("宽度")
        @Values({"100", "200", "300", "400", "500", "600"})
        private int width = 400;
        @Bindable @Title("文字")
        @Values({"按钮", "按钮文字", "按钮长长长长长长文字"})
        public String text = "按钮";
        @Bindable @Title("禁用")
        public boolean disabled = false;

        private ButtonsFragment fragment_;

        public Style(ButtonsFragment fragment) {
            this.fragment_ = fragment;
        }

        public void setWidth(int width) {
            this.width = width;
            notifyPropertyChanged(BR.width);
            fragment_.updateWidth();
        }

        public int getWidth() {
            return width;
        }
    }

    private void updateWidth() {
        RecyclerView listView = getView().findViewById(R.id.buttonsList);
        listView.measure(0,listView.getHeight());
        // TODO: 让按钮变小
    }

    public static class ButtonItemLayout extends RecyclerViewAdapter.UnitTypeItemLayout<Map.Entry<String, Integer>> {
        private final Style style;

        public ButtonItemLayout(Style style) {
            super(R.layout.button_item);
            this.style = style;
        }

        @Override
        public void bindView(ViewDataBinding binding, Map.Entry<String, Integer> item, int position) {
            super.bindView(binding, item, position);
            binding.setVariable(BR.style, style);
        }
    }

    // this should be in view model, but fragment may simplify things
    public RecyclerViewAdapter.OnItemClickListener buttonClicked = new RecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, Object object) {
            Log.d(TAG, "buttonClicked" + object);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.addObserver(this);
    }

    @Override
    public void onDestroy() {
        SkinManager.removeObserver(this);
        super.onDestroy();
    }

    @Override
    public void updateSkin(SkinObservable observable, Object o) {
        getBinding().buttonsList.getAdapter().notifyItemRangeChanged(0, getModel().styles.size());
    }
}
