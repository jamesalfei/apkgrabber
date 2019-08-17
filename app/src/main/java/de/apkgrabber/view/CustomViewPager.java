package de.apkgrabber.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import de.apkgrabber.updater.UpdaterOptions;


public class CustomViewPager
        extends ViewPager {


    public CustomViewPager(
            Context context
    ) {
        super(context);
    }


    public CustomViewPager(
            Context context,
            AttributeSet attrs
    ) {
        super(context, attrs);
    }


    @Override
    public void setCurrentItem(
            int item,
            boolean smoothScroll
    ) {
        super.setCurrentItem(item, !new UpdaterOptions(getContext()).disableAnimations());
    }


    @Override
    public void setCurrentItem(
            int item
    ) {
        super.setCurrentItem(item, !new UpdaterOptions(getContext()).disableAnimations());
    }


}

