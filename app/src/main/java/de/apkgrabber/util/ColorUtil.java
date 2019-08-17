package de.apkgrabber.util;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;


public class ColorUtil {


    public static int getColorFromTheme(
            Resources.Theme theme,
            int color_id
    ) {
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(color_id, typedValue, true);
        return typedValue.data;
    }


    public static int getColorFromContext(
            Context context,
            int color_id
    ) {
        int[] textSizeAttr = new int[]{color_id};
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }


    public static String getHexStringFromInt(
            int color
    ) {
        return "#" + String.format("%06X", 0xFFFFFF & color);
    }


    public static Drawable tintDrawable(
            Context context,
            Drawable icon,
            int resColor
    ) {
        DrawableCompat.setTint(DrawableCompat.wrap(icon), ColorUtil.getColorFromContext(context, resColor));
        return icon;
    }


}

