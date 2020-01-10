package com.befree.carefree.data;

import com.befree.carefree.model.Quote;


import java.util.ArrayList;

public interface QuoteListAsyncResponse {
    void processFinished(ArrayList<Quote> quotes);
}
