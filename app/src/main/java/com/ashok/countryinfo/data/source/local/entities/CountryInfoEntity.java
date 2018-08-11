package com.ashok.countryinfo.data.source.local.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Eval-Ranjitha on 11-08-2018.
 */

@Entity(tableName = "country_info", foreignKeys = @ForeignKey(entity = CountryEntity.class,
        parentColumns = "id",
        childColumns = "country_id",
        onDelete = ForeignKey.CASCADE))
public class CountryInfoEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "info_id")
    public int infoId;

    @ColumnInfo(name = "country_id")
    public int countryId;

    public String title;
    public String description;

    @ColumnInfo(name = "image_url")
    public String imageHref;

}
