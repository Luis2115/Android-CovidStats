package com.reymi.covid_stats_global.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reymi.covid_stats_global.Models.GlobalStats;
import com.reymi.covid_stats_global.R;

import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {
    private List<GlobalStats> globalStatsList;

    public StatsAdapter(List<GlobalStats> globalStatsList) {
        this.globalStatsList = globalStatsList;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_global_stats, parent, false);

        return new StatsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsAdapter.StatsViewHolder holder, int position) {
        holder.Stats(globalStatsList.get(position));
    }

    @Override
    public int getItemCount() {
        return globalStatsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class StatsViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtCant;
        ImageView img;
        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.tvName);
            txtCant = itemView.findViewById(R.id.tvCant);
            img = itemView.findViewById(R.id.imageView1);
        }

        void Stats(GlobalStats globalStats){
            txtName.setText(globalStats.getNombre());
            txtCant.setText(globalStats.getCantidad());

            if (img.getResources() != null){
                img.setImageResource(globalStats.getImg());
                img.setVisibility(View.VISIBLE);
            } else{
                img.setVisibility(View.GONE);
            }
        }
    }
}
