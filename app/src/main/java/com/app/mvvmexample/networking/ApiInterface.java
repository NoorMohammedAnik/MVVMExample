package com.app.mvvmexample.networking;

import com.app.mvvmexample.model.HolidayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("PublicHolidays/2021/us")
    Call<List<HolidayModel>> getHolidays();


}
