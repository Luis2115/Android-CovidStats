package com.reymi.covid_stats_global.Interface;

import com.reymi.covid_stats_global.Models.GlobalStats;

import retrofit2.Call;
import retrofit2.http.GET;

@FunctionalInterface
public interface GlobalStatsApi {

    @GET("v3/covid-19/all")
    Call<GlobalStats> fetchData();
}
