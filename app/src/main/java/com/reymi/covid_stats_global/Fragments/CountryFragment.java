package com.reymi.covid_stats_global.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reymi.covid_stats_global.Adapters.CountryAdapter;
import com.reymi.covid_stats_global.Interface.CountryAPI;
import com.reymi.covid_stats_global.Models.ResponseCountry;
import com.reymi.covid_stats_global.R;

import java.util.ArrayList;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryFragment extends Fragment {

    View view;
    public static ArrayList<ResponseCountry> responseCountryList = new ArrayList<>();
    RecyclerView countryRecyclerView;
    EditText search;
    CircularProgressBar circularProgressBar;

    CountryAdapter adapter;

    public CountryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_country, container, false);

        initComponents();

        cargar();

        find();

        return view;
    }

    private void cargar() {
        adapter = new CountryAdapter(responseCountryList, getContext(), new CountryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Hola", Toast.LENGTH_SHORT).show();
            }
        });

        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        countryRecyclerView.setAdapter(adapter);
    }

    private void find() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://disease.sh/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CountryAPI countryAPI = retrofit.create(CountryAPI.class);

        Call<ArrayList<ResponseCountry>> call = countryAPI.data();

        call.enqueue(new Callback<ArrayList<ResponseCountry>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseCountry>> call, Response<ArrayList<ResponseCountry>> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            //Log.e("validar", res.getCountry());
                            responseCountryList.addAll(response.body());
                            circularProgressBar.stopNestedScroll();
                            circularProgressBar.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    circularProgressBar.stopNestedScroll();
                    circularProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ResponseCountry>> call, Throwable t) {
                circularProgressBar.stopNestedScroll();
                circularProgressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Por Favor, Active sus Datos Moviles", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initComponents() {
        search = view.findViewById(R.id.edtSearch);
        countryRecyclerView = view.findViewById(R.id.recyclerView);
        circularProgressBar = view.findViewById(R.id.loader);

        fetchData();
    }
}