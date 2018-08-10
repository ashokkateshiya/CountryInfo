package com.ashok.countryinfo.data.source;

import android.support.annotation.NonNull;

public class CountryRepo implements CountryDataSource {
    private static CountryRepo INSTANCE;

    public synchronized static CountryRepo getInstance(@NonNull CountryDataSource countryDataSource) {
        if (INSTANCE != null) return INSTANCE;
        return INSTANCE = new CountryRepo(countryDataSource);
    }

    private CountryRepo(@NonNull CountryDataSource countryDataSource) {
        this.mCountryNetwrokDataSource = countryDataSource;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private final CountryDataSource mCountryNetwrokDataSource;

    @Override
    public void getCountryInfo(CountryInfoCallbacks countryInfoCallbacks) {
        mCountryNetwrokDataSource.getCountryInfo(countryInfoCallbacks);
    }
}
