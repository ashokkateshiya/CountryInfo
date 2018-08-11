package com.ashok.countryinfo.data.source;

import android.support.annotation.NonNull;

import com.ashok.countryinfo.data.CountryInfo;
import com.ashok.countryinfo.data.source.local.CountryLocalDataSource;

public class CountryRepo implements CountryInfoRepoDataSource {
    private static CountryRepo INSTANCE;

    public synchronized static CountryRepo getInstance(@NonNull CountryDataSource countryDataSource, @NonNull CountryLocalDataSource countryLocalDataSource) {
        if (INSTANCE != null) return INSTANCE;
        return INSTANCE = new CountryRepo(countryDataSource, countryLocalDataSource);
    }

    private CountryRepo(@NonNull CountryDataSource countryDataSource, @NonNull CountryLocalDataSource countryLocalDataSource) {
        this.mCountryNetwrokDataSource = countryDataSource;
        this.mCountryLocalDataSource = countryLocalDataSource;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private final CountryDataSource mCountryNetwrokDataSource;
    private final CountryLocalDataSource mCountryLocalDataSource;
    private boolean refresh = false;

    @Override
    public void getCountryInfo(final CountryInfoCallbacks countryInfoCallbacks) {
        if (refresh) {
            loadCountryInfoFromNetwork(true, countryInfoCallbacks);
            refresh = false;
            return;
        }

        mCountryLocalDataSource.getCountryInfo(new CountryInfoCallbacks() {
            @Override
            public void onCountryInfo(@NonNull CountryInfo countryInfo) {
                countryInfoCallbacks.onCountryInfo(countryInfo);
            }

            @Override
            public void dataNotAvailable() {
                loadCountryInfoFromNetwork(true, countryInfoCallbacks);
            }
        });

    }

    @Override
    public void refreshCountryInfo() {
        refresh = true;
    }

    private void loadCountryInfoFromNetwork(final boolean cache, final CountryInfoCallbacks callbacks) {
        mCountryNetwrokDataSource.getCountryInfo(new CountryInfoCallbacks() {
            @Override
            public void onCountryInfo(@NonNull CountryInfo countryInfo) {
                if (cache) {
                    mCountryLocalDataSource.insertCountryInfo(countryInfo, null);
                }
                callbacks.onCountryInfo(countryInfo);
            }

            @Override
            public void dataNotAvailable() {
                callbacks.dataNotAvailable();
            }
        });
    }
}
