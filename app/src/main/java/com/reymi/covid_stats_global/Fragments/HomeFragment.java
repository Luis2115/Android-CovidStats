package com.reymi.covid_stats_global.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reymi.covid_stats_global.Adapters.StatsAdapter;
import com.reymi.covid_stats_global.Interface.GlobalStatsApi;
import com.reymi.covid_stats_global.Models.GlobalStats;
import com.reymi.covid_stats_global.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    View view;
    List<GlobalStats> globalStatsList;
    RecyclerView globalRecyclerView;
    CircularProgressBar circularProgressBar;
    PieChart pieChart;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initComponents();

        globalRecyclerView.setAdapter(new StatsAdapter(globalStatsList));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://disease.sh/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        GlobalStatsApi globalStatsApi = retrofit.create(GlobalStatsApi.class);
        Call<GlobalStats> call = globalStatsApi.fetchData();

        call.enqueue(new Callback<GlobalStats>() {
            @Override
            public void onResponse(@NonNull Call<GlobalStats> call, @NonNull Response<GlobalStats> response) {
                try {
                    if (response.isSuccessful()) {
                        GlobalStats globalStats = response.body();
                        if (globalStats != null) {
                            globalStatsList.add(new GlobalStats("Casos", globalStats.getCases(), R.drawable.ic_cases));
                            globalStatsList.add(new GlobalStats("Recuperados", globalStats.getRecovered(), R.drawable.ic_recovered));
                            globalStatsList.add(new GlobalStats("Criticos", globalStats.getCritical(), R.drawable.ic_critico));
                            globalStatsList.add(new GlobalStats("Activos", globalStats.getActive(), R.drawable.ic_active));
                            globalStatsList.add(new GlobalStats("Casos al Día", globalStats.getTodayCases(), R.drawable.ic_cases_day));
                            globalStatsList.add(new GlobalStats("Total de Muertes", globalStats.getDeaths(), R.drawable.ic_death));
                            globalStatsList.add(new GlobalStats("Muertes en el Día", globalStats.getTodayDeaths(), R.drawable.ic_deathdays));
                            globalStatsList.add(new GlobalStats("Ciudades Afectadas", globalStats.getAffectedCountries(), R.drawable.ic_country2));

                            pieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(globalStats.getCases()), Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(globalStats.getRecovered()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(globalStats.getDeaths()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(globalStats.getActive()), Color.parseColor("#29B6F6")));
                            pieChart.startAnimation();

                            circularProgressBar.stopNestedScroll();
                            circularProgressBar.setVisibility(View.GONE);
                            globalRecyclerView.setVisibility(View.VISIBLE);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    circularProgressBar.stopNestedScroll();
                    circularProgressBar.setVisibility(View.GONE);
                    globalRecyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GlobalStats> call, @NonNull Throwable t) {
                circularProgressBar.stopNestedScroll();
                circularProgressBar.setVisibility(View.GONE);
                globalRecyclerView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Por Favor, Active sus Datos Moviles", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initComponents() {
        globalRecyclerView = view.findViewById(R.id.recyclerDatos);
        pieChart = view.findViewById(R.id.globalStats);
        circularProgressBar = view.findViewById(R.id.loader);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        globalRecyclerView.setLayoutManager(gridLayoutManager);

        globalStatsList = new ArrayList<>();

        fetchData();
        /*
        globalStatsList.add(new GlobalStats("Casos", "1", R.drawable.ic_cases));
        globalStatsList.add(new GlobalStats("Recuperados", "2", R.drawable.ic_recovered));
        globalStatsList.add(new GlobalStats("Criticos", "3", R.drawable.ic_critico));
        globalStatsList.add(new GlobalStats("Activos", "4", R.drawable.ic_active));
        globalStatsList.add(new GlobalStats("Casos al Día", "5", R.drawable.ic_cases_day));
        globalStatsList.add(new GlobalStats("Total de Muertes", "6", R.drawable.ic_death));
        globalStatsList.add(new GlobalStats("Muertes en el Día", "7", R.drawable.ic_deathdays));
        globalStatsList.add(new GlobalStats("Ciudades Afectadas", "8", R.drawable.ic_country2));
         */

    }
}