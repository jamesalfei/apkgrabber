package de.apkgrabber.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.apkgrabber.R;
import de.apkgrabber.model.LogMessage;

import java.text.DateFormat;
import java.util.Date;

import static java.text.DateFormat.getDateTimeInstance;

public class LogView extends LinearLayout {

	private TextView mTitle;
	private TextView mTime;
	private TextView mMessage;
	private ImageView mIcon;

	public LogView(Context context) {
		super(context);

		mTitle = findViewById(R.id.log_title);
		mTime = findViewById(R.id.log_time);
		mMessage = findViewById(R.id.log_message);
		mIcon = findViewById(R.id.log_icon);
	}

	public static LogView build(Context context) {
		LogView instance = new LogView(context);
		instance.onFinishInflate();
		return instance;
	}

	public void bind(LogMessage message) {
		mTitle.setText(message.getTitle());
		mMessage.setText(message.getMessage());

		DateFormat df = getDateTimeInstance();
		mTime.setText(df.format(new Date(message.getTime())));

		if (message.getSeverity() == LogMessage.SEVERITY_INFO) {
			mIcon.setImageResource(R.drawable.ic_info);
			mIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.infoColor));
		} else if (message.getSeverity() == LogMessage.SEVERITY_WARNING) {
			mIcon.setImageResource(R.drawable.ic_warning);
			mIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.warningColor));
		} else if (message.getSeverity() == LogMessage.SEVERITY_ERROR) {
			mIcon.setImageResource(R.drawable.ic_error);
			mIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.errorColor));
		}
	}
}

