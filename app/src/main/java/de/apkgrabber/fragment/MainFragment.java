package de.apkgrabber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import com.squareup.otto.Subscribe;
import de.apkgrabber.R;
import de.apkgrabber.adapter.MainActivityPageAdapter;
import de.apkgrabber.event.InstalledAppTitleChange;
import de.apkgrabber.event.SearchTitleChange;
import de.apkgrabber.event.UpdaterTitleChange;
import de.apkgrabber.model.AppState;
import de.apkgrabber.util.MyBus;
import de.apkgrabber.view.CustomViewPager;
import org.androidannotations.annotations.*;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

	@ViewById(R.id.container)
	CustomViewPager mViewPager;

	@ViewById(R.id.tabs)
	TabLayout mTabLayout;

	@Bean
	AppState mAppState;

	@Bean
	MyBus mBus;

	Bundle mSavedInstance;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		mSavedInstance = savedInstanceState;
		super.onCreate(savedInstanceState);
		mBus.register(this);
	}

	@Override
	public void onDestroy() {
		mBus.unregister(this);
		super.onDestroy();
	}

	@AfterViews
	void init() {
		mViewPager.setAdapter(new MainActivityPageAdapter(getContext(), getChildFragmentManager()));
		mViewPager.setOffscreenPageLimit(2);
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				mAppState.setSelectedTab(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});
		selectTab(mAppState.getSelectedTab());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onResume() {
		super.onResume();

		// Select tab
		selectTab(mAppState.getSelectedTab());
	}

	@Subscribe
	public void onInstalledAppTitleChange(InstalledAppTitleChange t) {
		if (mTabLayout != null) {
			TabLayout.Tab selectedTab = mTabLayout.getTabAt(0);
			if (selectedTab != null) {
				selectedTab.setText(t.getTitle());
			}
		}
	}

	@Subscribe
	public void onUpdaterTitleChange(UpdaterTitleChange t) {
		if (mTabLayout != null) {
			TabLayout.Tab selectedTab = mTabLayout.getTabAt(1);
			if (selectedTab != null) {
				selectedTab.setText(t.getTitle());
			}
		}
	}

	@Subscribe
	public void onSearchTitleChange(SearchTitleChange t) {
		if (mTabLayout != null) {
			TabLayout.Tab selectedTab = mTabLayout.getTabAt(2);
			if (selectedTab != null) {
				selectedTab.setText(t.getTitle());
			}
		}
	}

	@UiThread
	public void selectTab(int tab) {
		if (mTabLayout != null) {
			TabLayout.Tab selectedTab = mTabLayout.getTabAt(tab);
			if (selectedTab != null) {
				selectedTab.select();
			}
		}
	}

}


