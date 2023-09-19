package com.nike.products.businesslogic.di;


import static com.nike.products.utils.StaticData.getBaseURL;

import android.content.Context;


import com.nike.products.businesslogic.Network.ApiCallFactory;
import com.nike.products.businesslogic.Network.ApiHelper;
import com.nike.products.businesslogic.rx.AppSchedulerProvider;
import com.nike.products.businesslogic.rx.SchedulerProvider;
import com.nike.products.utils.preference.UtilsSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    ApiHelper providesRetrofitInterface() {
        return ApiCallFactory.create(getBaseURL());
    }

    @Provides
    SchedulerProvider providesScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    UtilsSharedPreferences providesPreferences(Context context) {
        return new UtilsSharedPreferences(context);
    }
}
