package com.ashok.countryinfo.country;

import android.support.annotation.NonNull;

import com.ashok.countryinfo.data.CountryInfo;
import com.ashok.countryinfo.data.InfoRow;
import com.ashok.countryinfo.data.source.CountryDataSource;
import com.ashok.countryinfo.data.source.CountryRepo;

import java.util.ArrayList;

/**
 * Created by Ashok on 04-08-2018.
 */

public class CountryInfoPresenter implements CountryContract.Presenter {
    private final CountryRepo mCountryRepo;
    private final CountryContract.View mCountryView;

    public CountryInfoPresenter(@NonNull CountryRepo repo, CountryContract.View view) {
        this.mCountryRepo = repo;
        this.mCountryView = view;
    }

    @Override
    public void start() {
        loadCountryInfo();
    }

    @Override
    public void loadCountryInfo() {
        mCountryView.showLoading(true);
        mCountryRepo.getCountryInfo(new CountryDataSource.CountryInfoCallbacks() {
            @Override
            public void onCountryInfo(@NonNull CountryInfo countryInfo) {
                if (!mCountryView.isActive()) {
                    return;
                }

                mCountryView.showLoading(false);

                mCountryView.showTitle(countryInfo.getTitle());

                ArrayList<InfoRow> infoRows = new ArrayList<>();
                for(InfoRow i : countryInfo.getRows()){
                    if(i.getTitle() != null){
                        infoRows.add(i);
                    }
                }
                mCountryView.showCountryInfo(infoRows);
            }

            @Override
            public void dataNotAvailable() {
                if (!mCountryView.isActive()) {
                    return;
                }

                mCountryView.showLoading(false);

                mCountryView.showLoadingCountryError();
            }
        });

    }
}
