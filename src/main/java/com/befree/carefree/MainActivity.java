package com.befree.carefree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import com.befree.carefree.data.QuoteData;
import com.befree.carefree.data.QuoteListAsyncResponse;
import com.befree.carefree.data.QuoteViewPagerAdpater;
import com.befree.carefree.model.Quote;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuoteViewPagerAdpater quoteViewPagerAdpater;
    private ViewPager viewPager;
    Context context;
    public  int  MY_REQUEST_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        quoteViewPagerAdpater = new QuoteViewPagerAdpater(getSupportFragmentManager(), getFragments());

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(quoteViewPagerAdpater);

        getversion();

    }

    private void getversion() {
        // Creates instance of the manager.
        final AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(context);

// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        // For a flexible update, use AppUpdateType.FLEXIBLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    // Request the update.
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                // Pass the intent that is returned by 'getAppUpdateInfo()'.
                                appUpdateInfo,
                                // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                                AppUpdateType.IMMEDIATE,
                                // The current activity making the update request.
                                MainActivity.this,
                                // Include a request code to later monitor this update request.
                                MY_REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                        Log.e("update", String.valueOf(e));
                    }

                }
            }
        });
    }



    private List<Fragment> getFragments() {
        final List<Fragment> fragmentList = new ArrayList<>();
        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {
                for (int i = 0; i < quotes.size(); i++) {
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );
                    fragmentList.add(quoteFragment);
                }
                quoteViewPagerAdpater.notifyDataSetChanged();/// very important!!

            }
        });




        return fragmentList;
    }
}
