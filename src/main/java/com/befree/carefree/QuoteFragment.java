package com.befree.carefree;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {


    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);

        TextView quoteText = quoteView.findViewById(R.id.quoteText);
        TextView byAuthor = quoteView.findViewById(R.id.byAuthor);


        String quote = getArguments().getString("quote");
        String author = getArguments().getString("author");

        int colors[] = new int[] {R.color.red_50, R.color.pink_50, R.color.purple_100,
                R.color.dark_purple_50, R.color.indigo_50, R.color.blue_100, R.color.light_green_200,
                R.color.lime_A200};

        quoteText.setText(quote);
        byAuthor.setText("-" + author);

      // quoteText.setTextColor(getRandomQuote(colors));


        return quoteView;
    }

    public static final QuoteFragment newInstance(String quote, String author) {
        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle =  new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);

        return fragment;

    }

    int getRandomQuote(int[] colorArray) {
        int color;
        int quotesArrayLen = colorArray.length;

        int randomNum = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            randomNum = ThreadLocalRandom.current().nextInt(quotesArrayLen);
        }

        color = colorArray[randomNum];

        return  color;
    }

}
