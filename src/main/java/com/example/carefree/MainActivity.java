package com.example.carefree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.carefree.data.QuoteData;
import com.example.carefree.data.QuoteListAsyncResponse;
import com.example.carefree.data.QuoteViewPagerAdpater;
import com.example.carefree.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private QuoteViewPagerAdpater quoteViewPagerAdpater;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteViewPagerAdpater = new QuoteViewPagerAdpater(getSupportFragmentManager(), getFragments());

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(quoteViewPagerAdpater);


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
