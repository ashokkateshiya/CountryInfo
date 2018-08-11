package com.ashok.countryinfo.data.source.local;

import android.support.annotation.NonNull;

import com.ashok.countryinfo.data.CountryInfo;
import com.ashok.countryinfo.data.InfoRow;
import com.ashok.countryinfo.data.source.CountryDataSource;
import com.ashok.countryinfo.data.source.local.entities.CountryEntity;
import com.ashok.countryinfo.data.source.local.entities.CountryInfoEntity;
import com.ashok.countryinfo.utils.AppExecutors;

import java.util.ArrayList;

public class CountryLocalDataSource implements CountryDataSource {
    private static CountryLocalDataSource countryDataSource;
    private CountryInfoDao mCountryInfoDao;
    private AppExecutors mAppExecutors;

    private CountryLocalDataSource(@NonNull AppExecutors executors, @NonNull CountryInfoDao countryInfoDao) {
        this.mCountryInfoDao = countryInfoDao;
        this.mAppExecutors = executors;
    }

    public static CountryLocalDataSource getInstance(@NonNull AppExecutors executors, @NonNull CountryInfoDao countryInfoDao) {
        if (countryDataSource != null) return countryDataSource;
        return countryDataSource = new CountryLocalDataSource(executors, countryInfoDao);
    }

    @Override
    public void getCountryInfo(final CountryInfoCallbacks countryInfoCallbacks) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                CountryEntity[] country = mCountryInfoDao.getCountry();
                if (country != null && country.length > 0) {
                    CountryEntity ci = country[0];
                    CountryInfoEntity[] cie = mCountryInfoDao.getCountryInfo(ci.id);

                    CountryInfo info = new CountryInfo();
                    info.setTitle(ci.title);
                    ArrayList<InfoRow> rows = new ArrayList<>();
                    if (cie != null) {
                        for (CountryInfoEntity e : cie) {
                            rows.add(new InfoRow(e.title, e.description, e.imageHref));
                        }
                        info.setRows(rows);
                    }

                    countryInfoCallbacks.onCountryInfo(info);
                } else {
                    countryInfoCallbacks.dataNotAvailable();
                }
            }
        });

    }

    public void insertCountryInfo(final CountryInfo info, final CountryInfoInsertCallBacks insertCallBacks) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {

                CountryEntity ce = new CountryEntity();
                ce.id = 1;
                ce.title = info.getTitle();

                mCountryInfoDao.insertCountry(ce);

                ArrayList<CountryInfoEntity> ciel = new ArrayList<>();
                for (InfoRow i : info.getRows()) {
                    CountryInfoEntity cie = new CountryInfoEntity();
                    cie.countryId = ce.id;
                    cie.title = i.getTitle();
                    cie.description = i.getDescription();
                    cie.imageHref = i.getDescription();

                    ciel.add(cie);
                }

                mCountryInfoDao.insertCountryInfo(ciel);

                if (insertCallBacks != null)
                    insertCallBacks.onDataInserted();
            }
        });
    }
}
