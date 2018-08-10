package com.ashok.countryinfo.data.source.network;

import com.ashok.countryinfo.data.CountryInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ashok on 04-08-2018.
 */

public interface APIService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<CountryInfo> getCountryInfo();

}
