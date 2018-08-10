package com.ashok.countryinfo;

import android.app.Application;

import com.ashok.countryinfo.data.source.CountryRepo;
import com.ashok.countryinfo.data.source.network.CountryNetworkDataSource;
import com.ashok.countryinfo.data.source.network.RetrofitHelper;


/**
 * Created by Ashok on 04-08-2018.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitHelper.initRetrofit();
    }

    public static CountryRepo getCountryRepo(){
        return CountryRepo.getInstance(CountryNetworkDataSource.getInstance());
    }
}
