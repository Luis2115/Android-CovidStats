package com.reymi.covid_stats_global.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reymi.covid_stats_global.Adapters.StatsAdapter;
import com.reymi.covid_stats_global.Models.GlobalStats;
import com.reymi.covid_stats_global.R;

import org.eazegraph.lib.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

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

    private void initComponents(){
        globalRecyclerView = view.findViewById(R.id.recyclerDatos);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        globalRecyclerView.setLayoutManager(gridLayoutManager);

        globalStatsList = new ArrayList<GlobalStats>();

        globalStatsList.add(new GlobalStats("Casos", "1", R.drawable.ic_cases));
        globalStatsList.add(new GlobalStats("Recuperados", "2", R.drawable.ic_recovered));
        globalStatsList.add(new GlobalStats("Criticos", "3", R.drawable.ic_critico));
        globalStatsList.add(new GlobalStats("Activos", "4", R.drawable.ic_active));
        globalStatsList.add(new GlobalStats("Casos al Día", "5", R.drawable.ic_cases_day));
        globalStatsList.add(new GlobalStats("Total de Muertes", "6", R.drawable.ic_death));
        globalStatsList.add(new GlobalStats("Muertes en el Día", "7", R.drawable.ic_deathdays));
        globalStatsList.add(new GlobalStats("Ciudades Afectadas", "8", R.drawable.ic_country2));

    }
}