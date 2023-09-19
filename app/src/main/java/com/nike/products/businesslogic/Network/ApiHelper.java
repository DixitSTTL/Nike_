package com.nike.products.businesslogic.Network;

import com.nike.products.models.pojo.Get_search_pojo;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

public interface ApiHelper {

//    @GET(ApiEndPoints.API_HISTORY)
//    Single<Get_all_city_POJO> get_all_city_data(@QueryMap Map<String, Object> map);
//
//
//    @GET(ApiEndPoints.API_CURRENT)
//    Single<Get_current_POJO> get_current_data(@QueryMap Map<String, Object> map);
//
//    @GET(ApiEndPoints.API_FORECAST)
//    Single<Get_forecast_POJO> get_forecast_data(@QueryMap Map<String, Object> map);

    @GET(ApiEndPoints.API_SEARCH)
    Single<Get_search_pojo> get_search_data(@QueryMap Map<String, String> map, @HeaderMap Map<String,String> map2);


}
