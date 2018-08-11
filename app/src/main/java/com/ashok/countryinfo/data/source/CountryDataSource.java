package com.ashok.countryinfo.data.source;

import android.support.annotation.NonNull;

import com.ashok.countryinfo.data.CountryInfo;

public interface CountryDataSource {

    interface CountryInfoCallbacks {

        void onCountryInfo(@NonNull CountryInfo countryInfo);

        void dataNotAvailable();

    }

    interface CountryInfoInsertCallBacks {
        void onDataInserted();

        void onError();
    }

    void getCountryInfo(CountryInfoCallbacks countryInfoCallbacks);

}
