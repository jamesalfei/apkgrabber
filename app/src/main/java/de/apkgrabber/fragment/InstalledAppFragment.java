package de.apkgrabber.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import com.squareup.otto.Subscribe;
import de.apkgrabber.R;
import de.apkgrabber.adapter.InstalledAppAdapter;
import de.apkgrabber.event.InstalledAppTitleChange;
import de.apkgrabber.event.UpdateInstalledAppsEvent;
import de.apkgrabber.model.InstalledApp;
import de.apkgrabber.updater.UpdaterOptions;
import de.apkgrabber.util.GenericCallback;
import de.apkgrabber.util.InstalledAppUtil;
import de.apkgrabber.util.MyBus;
import org.androidannotations.annotations.*;

import java.util.List;


@EFragment(R.layout.fragment_installed_apps)
public class InstalledAppFragment
        extends Fragment {


    @ViewById(R.id.list_view)
    RecyclerView mRecyclerView;

    @ViewById(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bean
    InstalledAppUtil mInstalledAppUtil;

    @Bean
    MyBus mBus;


    @AfterViews
    void init(
    ) {
        mBus.register(this);
        updateInstalledApps(new UpdateInstalledAppsEvent());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateInstalledApps(new UpdateInstalledAppsEvent());
                Log.d("InstalledAppFragmnet", "refreshing...");
            }
        });
    }


    @Override
    public void onDestroy() {
        mBus.unregister(this);
        super.onDestroy();
    }


    @Subscribe
    public void updateInstalledApps(
            UpdateInstalledAppsEvent ev
    ) {
        mInstalledAppUtil.getInstalledAppsAsync(getContext(), new GenericCallback<List<InstalledApp>>() {
            @Override
            public void onResult(List<InstalledApp> items) {
                setListAdapter(items);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @UiThread(propagation = UiThread.Propagation.REUSE)
    protected void setListAdapter(
            List<InstalledApp> items
    ) {
        if (mRecyclerView == null || mBus == null) {
            return;
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (new UpdaterOptions(getContext()).disableAnimations()) {
            mRecyclerView.setItemAnimator(null);
        } else {
            ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        mRecyclerView.setAdapter(new InstalledAppAdapter(getContext(), mRecyclerView, items));
        mBus.post(new InstalledAppTitleChange(getString(R.string.tab_installed) + " (" + items.size() + ")"));
    }


}


