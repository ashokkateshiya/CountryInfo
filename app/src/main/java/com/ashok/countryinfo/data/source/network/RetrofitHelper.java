package com.ashok.countryinfo.data.source.network;

import android.util.Log;

import com.ashok.countryinfo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * <p>
 * Retrofit helper class
 */
@SuppressWarnings("unused")
final public class RetrofitHelper {

    private static RetrofitHelper helper;
    private APIService mService;

    private static String API_HOST = "https://dl.dropboxusercontent.com";

    public static String getHost() {
        return API_HOST;
    }

    private RetrofitHelper() {
        init();
    }

    public static synchronized void initRetrofit() {
        if (helper != null) return;
        helper = new RetrofitHelper();
    }

    public static void reset() {
        helper = null;
        initRetrofit();
    }

    /**
     * initialize retrofit. Called only once.
     * Call this method before use any network transaction
     */
    private void init() {
        if (mService != null) return;

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);


        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient httpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(API_HOST + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(APIService.class);

        Log.i(getClass().getName(), "Retrofit initialized");
    }

    /**
     *
     * @return all apis available for network transaction
     */
    public static APIService getServices() {
        if (helper == null || helper.mService == null)
            throw new IllegalStateException("retrofit service not initialize yet.");
        return helper.mService;
    }

}
