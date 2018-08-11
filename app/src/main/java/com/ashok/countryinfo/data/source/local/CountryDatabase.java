package com.ashok.countryinfo.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.ashok.countryinfo.data.source.local.entities.CountryEntity;
import com.ashok.countryinfo.data.source.local.entities.CountryInfoEntity;

/**
 * The Room Database that contains the Task table.
 */
@Database(entities = {CountryEntity.class, CountryInfoEntity.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class CountryDatabase extends RoomDatabase {

    private static CountryDatabase INSTANCE;

    public abstract CountryInfoDao countryInfoDao();

    private static final Object sLock = new Object();

    public static CountryDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        CountryDatabase.class, "Country.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}