package com.ashok.countryinfo.data.source;

/**
 * Created by Ashok on 11-08-2018.
 */

public interface CountryInfoRepoDataSource extends CountryDataSource{

    /**
     * refresh country information from server
     */
    void refreshCountryInfo();

}
