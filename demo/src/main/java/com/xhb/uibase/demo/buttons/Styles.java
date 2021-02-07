package com.xhb.uibase.demo.buttons;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.airbnb.paris.Paris;
import com.xhb.uibase.demo.R;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Styles {

    private static final String TAG = "Styles";

    public static Map<String, Integer> buttonStyles(Context context) {
        return getStyles(context, R.style.class, Pattern.compile("[Bb]utton"));
        // return getStyles(context, R.style.class, Pattern.compile("YellowLargeButtonStyle"));
    }

    public static Map<String, Integer> getStyles(Context context, Class<?> clazz, Pattern pattern) {
        Map<String, Integer> styles = new TreeMap<>();
        try {
            for (Field f : clazz.getDeclaredFields()) {
                if (pattern == null || pattern.matcher(f.getName()).find()) {
                    Log.d(TAG, f.getName());
                    int id = (Integer) f.get(clazz);
                    styles.put(f.getName(), id);
                }
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, "", e);
        }
        return styles;
    }

    @androidx.databinding.BindingAdapter("style")
    public static <T> void setStyle(TextView view, int style) {
        try {
            Paris.style(view).apply(style);
        } catch (Throwable e) {
            Log.w(TAG, "setStyle " + view.getContext().getResources().getResourceEntryName(style), e);
        }
    }

}