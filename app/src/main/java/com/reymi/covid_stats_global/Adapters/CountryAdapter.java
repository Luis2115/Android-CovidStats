package com.reymi.covid_stats_global.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reymi.covid_stats_global.Models.ResponseCountry;
import com.reymi.covid_stats_global.R;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context context;
    private OnItemClickListener itemClickListener;

    private ArrayList<ResponseCountry> countryModelsList;
    private ArrayList<ResponseCountry> countryModelsListFiltered;

    public CountryAdapter(Context context, OnItemClickListener listener, ArrayList<ResponseCountry> countryModelsList) {
        this.context = context;
        this.itemClickListener = listener;
        this.countryModelsList = countryModelsList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_list, null, false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        try {
            holder.bind(itemClickListener);
        } catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return countryModelsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFlag;
        TextView tvCountryName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageFlag = itemView.findViewById(R.id.imageFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
        }

        private void bind(final OnItemClickListener listener){
            this.tvCountryName.setText(countryModelsListFiltered.get(getAdapterPosition()).getCountry());
            Glide.with(context).load(countryModelsListFiltered.get(getAdapterPosition()).getCountryInfo().getFlag()).into(this.imageFlag);

            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
        }
    }


}
