package com.ashok.countryinfo.data.source;

import android.support.annotation.NonNull;

import com.ashok.countryinfo.data.CountryInfo;

public interface CountryDataSource {

    /**
     * country information load callback
     */
    interface CountryInfoCallbacks {

        /**
         *
         * @param countryInfo country information
         */
        void onCountryInfo(@NonNull CountryInfo countryInfo);

        /**
         * called when no country data available
         */
        void dataNotAvailable();

    }

    /**
     * Country information insert callbacks
     */
    interface CountryInfoInsertCallBacks {

        /**
         * called when data successfully inserted
         */
        void onDataInserted();

        /**
         * called when insert error occurs
         */
        void onError();
    }

    /**
     * Load country infromation form local/remote based on availability of data
     *
     * @param countryInfoCallbacks information callbacks to return data
     */
    void getCountryInfo(CountryInfoCallbacks countryInfoCallbacks);

}
