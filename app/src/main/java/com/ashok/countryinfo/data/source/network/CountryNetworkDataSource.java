package com.ashok.countryinfo.data.source.network;

import com.ashok.countryinfo.data.CountryInfo;
import com.ashok.countryinfo.data.source.CountryDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryNetworkDataSource implements CountryDataSource {
    private static CountryNetworkDataSource countryDataSource;

    private CountryNetworkDataSource() {
    }

    ;

    public static CountryNetworkDataSource getInstance() {
        if (countryDataSource != null) return countryDataSource;
        return countryDataSource = new CountryNetworkDataSource();
    }

    @Override
    public void getCountryInfo(final CountryInfoCallbacks countryInfoCallbacks) {
        RetrofitHelper.getServices().getCountryInfo().enqueue(
                new Callback<CountryInfo>() {
                    @Override
                    public void onResponse(Call<CountryInfo> call, Response<CountryInfo> response) {
                        if (response.isSuccessful()) {
                            CountryInfo countryResponse = response.body();
                            if (countryResponse != null) {
                                countryInfoCallbacks.onCountryInfo(countryResponse);
                            } else {
                                countryInfoCallbacks.dataNotAvailable();
                            }
                        } else {
                            countryInfoCallbacks.dataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<CountryInfo> call, Throwable t) {
                        countryInfoCallbacks.dataNotAvailable();
                    }
                }
        );
    }
}
