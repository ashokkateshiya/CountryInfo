package com.ashok.countryinfo.data.source.local.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ashok on 11-08-2018.
 */

@Entity(tableName = "country")
public class CountryEntity {

    @PrimaryKey
    public int id;

    public String title;
}
