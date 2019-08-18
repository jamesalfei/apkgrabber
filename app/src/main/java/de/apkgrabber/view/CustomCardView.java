package de.apkgrabber.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import de.apkgrabber.util.ThemeUtil;

public class CustomCardView extends CardView {

	public CustomCardView(Context context) {
		super(context);
		setCardBackgroundColor(ThemeUtil.getCardBackgroundColor(context));
	}

	public CustomCardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCardBackgroundColor(ThemeUtil.getCardBackgroundColor(context));
	}

	public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setCardBackgroundColor(ThemeUtil.getCardBackgroundColor(context));
	}

}

