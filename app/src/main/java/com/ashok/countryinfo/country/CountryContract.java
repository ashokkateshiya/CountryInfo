package com.ashok.countryinfo.country;


import android.support.annotation.NonNull;

import com.ashok.countryinfo.BasePresenter;
import com.ashok.countryinfo.BaseView;
import com.ashok.countryinfo.data.InfoRow;

import java.util.ArrayList;

/**
 * Created by Ashok on 04-08-2018.
 *
 * Country contract between view and presenter
 */

public interface CountryContract {

    interface View extends BaseView<Presenter> {

        /**
         * Display loading
         * @param active loading active status
         */
        void showLoading(boolean active);

        /**
         * Display title in activity
         * @param title activity title
         */
        void showTitle(@NonNull String title);

        /**
         * Displays country information
         * @param infoRows country information
         */
        void showCountryInfo(@NonNull ArrayList<InfoRow> infoRows);

        /**
         * Display no country information available
         */
        void showNoCountryInfoAvailable();

        /**
         * Display load information error
         */
        void showLoadingCountryError();

        /**
         *
         * @return active status of view
         */
        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        /**
         * loads country information
         */
        void loadCountryInfo();


        /**
         * refresh country information from server
         */
        void refreshCountryInfo();
    }
}
