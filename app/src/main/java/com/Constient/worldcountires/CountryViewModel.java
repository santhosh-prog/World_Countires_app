package com.Constient.worldcountires;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class CountryViewModel extends ViewModel {
    private CountryRepository repository;
    private LiveData<List<Country>> countries;

    public CountryViewModel() {
        repository = new CountryRepository();
        countries = repository.getAllCountries();
    }

    public LiveData<List<Country>> getAllCountries() {
        return countries;
    }
}
