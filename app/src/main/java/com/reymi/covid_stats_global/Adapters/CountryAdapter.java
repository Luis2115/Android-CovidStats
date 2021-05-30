package com.reymi.covid_stats_global.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reymi.covid_stats_global.Fragments.CountryFragment;
import com.reymi.covid_stats_global.Models.ResponseCountry;
import com.reymi.covid_stats_global.R;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {

    private Context context;
    private OnItemClickListener itemClickListener;

    private ArrayList<ResponseCountry> countryModelsList;
    private ArrayList<ResponseCountry> countryModelsListFiltered;

    public CountryAdapter(ArrayList<ResponseCountry> countryModelsList, Context context, OnItemClickListener listener ) {
        this.context = context;
        this.itemClickListener = listener;
        this.countryModelsList = countryModelsList;
        this.countryModelsListFiltered = countryModelsList;
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

    public interface OnItemClickListener {
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

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;

                } else {
                    List<ResponseCountry> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (ResponseCountry itemsModel : countryModelsList) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryModelsListFiltered = (ArrayList<ResponseCountry>) results.values;
                CountryFragment.responseCountryList = (ArrayList<ResponseCountry>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
