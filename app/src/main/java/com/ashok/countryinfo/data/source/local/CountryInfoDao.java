package com.ashok.countryinfo.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ashok.countryinfo.data.source.local.entities.CountryEntity;
import com.ashok.countryinfo.data.source.local.entities.CountryInfoEntity;

import java.util.ArrayList;

/**
 * Created by Ashok on 11-08-2018.
 */

@Dao
public interface CountryInfoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountryInfo(ArrayList<CountryInfoEntity> info);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(CountryEntity countryEntity);

    @Query("select * from country limit 1")
    CountryEntity[] getCountry();

    @Query("select * from country_info where country_id=:countryId")
    CountryInfoEntity[] getCountryInfo(int countryId);
}
