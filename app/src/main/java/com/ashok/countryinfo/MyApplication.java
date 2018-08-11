package com.ashok.countryinfo;

import android.app.Application;

import com.ashok.countryinfo.data.source.CountryRepo;
import com.ashok.countryinfo.data.source.local.CountryDatabase;
import com.ashok.countryinfo.data.source.local.CountryLocalDataSource;
import com.ashok.countryinfo.data.source.network.CountryNetworkDataSource;
import com.ashok.countryinfo.data.source.network.RetrofitHelper;
import com.ashok.countryinfo.utils.AppExecutors;


/**
 * Created by Ashok on 04-08-2018.
 */

public class MyApplication extends Application{

    private static CountryDatabase mDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = CountryDatabase.getInstance(this);
        RetrofitHelper.initRetrofit();
    }

    public static CountryRepo getCountryRepo(){
        return CountryRepo.getInstance(CountryNetworkDataSource.getInstance(),
                CountryLocalDataSource.getInstance(new AppExecutors(), mDatabase.countryInfoDao()));
    }
}
