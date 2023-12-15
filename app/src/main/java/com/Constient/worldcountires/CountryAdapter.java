package com.Constient.worldcountires;



import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private List<Country> countryList;
    private List<Country> originalCountryList;


    public CountryAdapter(List<Country> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = countryList.get(position);
        if (country != null) {

            JsonObject nameObject = country.getName();
            JsonObject flagsObject = country.getFlags();
            if (nameObject != null && nameObject.has("common")) {
                holder.countryName.setText("Name: "+nameObject.get("common").getAsString());
            } else {
                holder.countryName.setText("N/A");
            }
            List<String> capitals = country.getCapital();
            if (capitals != null && !capitals.isEmpty()) {
                holder.capital.setText("Capital: " + capitals.get(0));
            } else {
                holder.capital.setText("Capital: Not available in Api");
            }
            holder.population.setText("Population: " + country.getPopulation());
            holder.region.setText("Region: " + country.getRegion());

            List<String> continents = country.getContinents();
            if (continents != null && !continents.isEmpty()) {
                String continent = continents.get(0);
                holder.currency.setText("Continent: "+continent);
            }
            if (flagsObject != null && flagsObject.has("png")) {

                String flagUrl = flagsObject.get("png").getAsString();

                Picasso.get().load(flagUrl).into(holder.flagImageView);
            } else {

            }

        } else {
            Log.d("adapter error null", "onBindViewHolder:  country is null");
        }
    }

    public void setCountryList(List<Country> countries) {
        this.countryList = countries;
        this.originalCountryList = new ArrayList<>(countries);
        notifyDataSetChanged();
    }

    public void filter(String query) {
        List<Country> filteredList = new ArrayList<>();

        for (Country country : originalCountryList) {
            if (country.getName().get("common").getAsString().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(country);
            }
        }

        countryList = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView countryName;
        private final TextView capital;
        private final TextView population;
        private final TextView region;
        private final TextView currency;
        private final ImageView flagImageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName=itemView.findViewById(R.id.countryNameTextView);
            capital = itemView.findViewById(R.id.capitalTextView);
            population = itemView.findViewById(R.id.populationTextView);
            region = itemView.findViewById(R.id.regionTextView);
            currency = itemView.findViewById(R.id.currencyTextView);
            flagImageView=itemView.findViewById(R.id.flagImageView);
        }
    }
}

