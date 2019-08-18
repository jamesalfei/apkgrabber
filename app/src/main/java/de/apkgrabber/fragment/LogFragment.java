package de.apkgrabber.fragment;

import android.support.v4.app.Fragment;
import android.widget.ListView;
import com.squareup.otto.Subscribe;
import de.apkgrabber.R;
import de.apkgrabber.adapter.LogAdapter;
import de.apkgrabber.model.LogMessage;
import de.apkgrabber.util.AnimationUtil;
import de.apkgrabber.util.MyBus;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_log)
public class LogFragment extends Fragment {

	@ViewById(R.id.list_view)
	ListView mListView;

	@Bean
	LogAdapter mAdapter;

	@Bean
	MyBus mBus;

	@AfterViews
	public void init() {
		mListView.setAdapter(mAdapter);
		mBus.register(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mBus.unregister(this);
	}

	@Subscribe
	public void onMessage(LogMessage message) {
		AnimationUtil.startSlideAnimation(getContext(), mListView);
		mAdapter.add(message);
		mAdapter.notifyDataSetChanged();
	}

}


