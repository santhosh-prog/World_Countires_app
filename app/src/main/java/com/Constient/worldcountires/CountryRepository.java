package com.Constient.worldcountires;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class CountryRepository {
    private ApiService apiService;
    private MutableLiveData<List<Country>> countries = new MutableLiveData<>();

    public CountryRepository() {
        this.apiService = ApiClient.getApiService();
    }


    public LiveData<List<Country>> getAllCountries() {
        apiService.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    countries.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
            }
        });

        return countries;
    }
}
