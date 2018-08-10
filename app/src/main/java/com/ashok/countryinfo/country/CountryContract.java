package com.ashok.countryinfo.country;


import android.support.annotation.NonNull;

import com.ashok.countryinfo.BasePresenter;
import com.ashok.countryinfo.BaseView;
import com.ashok.countryinfo.data.InfoRow;

import java.util.ArrayList;

/**
 * Created by Ashok on 04-08-2018.
 */

public interface CountryContract {

    interface View extends BaseView<Presenter> {
        void showLoading(boolean active);
        void showTitle(@NonNull String title);
        void showCountryInfo(@NonNull ArrayList<InfoRow> infoRows);
        void showNoCountryInfoAvailable();
        void showLoadingCountryError();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadCountryInfo();
    }
}
