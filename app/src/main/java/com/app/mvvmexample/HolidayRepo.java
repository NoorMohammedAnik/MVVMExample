package com.app.mvvmexample;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.app.mvvmexample.model.HolidayModel;
import com.app.mvvmexample.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayRepo {

    private final String TAG = getClass().getSimpleName();

    public androidx.lifecycle.MutableLiveData<List<HolidayModel>> requestHolidays() {
        final MutableLiveData<List<HolidayModel>> mutableLiveData = new MutableLiveData<>();

        ApiInterface apiService =
                MyApplication.getRetrofitClient().create(ApiInterface.class);

        apiService.getHolidays().enqueue(new Callback<List<HolidayModel>>() {
            @Override
            public void onResponse(Call<List<HolidayModel>> call, Response<List<HolidayModel>> response) {
                Log.e(TAG, "getCurrencyList response=" + response);

                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "requestHolidays response.size=" + response.body().size());
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<HolidayModel>> call, Throwable t) {
                Log.e(TAG, "getProdList onFailure" + call.toString());
            }
        });

        return mutableLiveData;
    }
}
