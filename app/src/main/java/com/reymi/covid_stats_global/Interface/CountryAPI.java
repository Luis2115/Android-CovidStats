package com.reymi.covid_stats_global.Interface;

import com.reymi.covid_stats_global.Models.ResponseCountry;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryAPI {

    @GET("v3/covid-19/countries")
    Call<ArrayList<ResponseCountry>> data();
}
