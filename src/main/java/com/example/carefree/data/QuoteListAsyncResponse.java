package com.example.carefree.data;

import com.example.carefree.model.Quote;


import java.util.ArrayList;

public interface QuoteListAsyncResponse {
    void processFinished(ArrayList<Quote> quotes);
}
